package com.ritoinfo.framework.evo.sp.sms.bizz;

import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.sp.sms.config.SmsConfig;
import com.ritoinfo.framework.evo.sp.sms.dto.SmsDto;
import com.ritoinfo.framework.evo.sp.sms.exception.SmsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: Kyll
 * Date: 2018-06-05 19:48
 */
@Slf4j
@Service
public class SmsBizz {
	@Autowired
	private SmsConfig smsConfig;

	public void send(SmsDto smsDto) {
		String queryString = smsConfig.getAddress() +
				"?comCode=" + smsConfig.getComCode() +
				"&orgCode=" + smsConfig.getOrgCode() +
				"&certiNo=" + DateUtil.formatDatetimeCompact(DateUtil.now()) +
				"&businessNo=" + smsConfig.getBusinessNo() +
				"&businessTypeCode=" + smsConfig.getBusinessTypeCode() +
				"&channel=" + smsConfig.getChannel() +
				"&phoneNo=" + smsDto.getPhoneNo() +
				"&content=" + smsDto.getContent();
		log.info("发送短信验证码: " + queryString);

		URLConnection connection;
		try {
			URL url = new URL(queryString);
			connection = url.openConnection();
			connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		} catch (MalformedURLException e) {
			throw new SmsException("解析地址失败: " + queryString, e);
		} catch (IOException e) {
			throw new SmsException("打开连接失败", e);
		} catch (Exception e) {
			throw new SmsException("建立连接失败", e);
		}

		connection.setDoOutput(true);
		connection.setDoInput(true);

		OutputStream outputStream;
		try {
			outputStream = connection.getOutputStream();
		} catch (IOException e) {
			throw new SmsException("获取输出流失败", e);
		}

		PrintWriter out = new PrintWriter(outputStream);
		out.print(smsDto.getContent());
		out.flush();
		out.close();

		InputStream inputStream;
		try {
			inputStream = connection.getInputStream();
		} catch (IOException e) {
			throw new SmsException("获取输入流失败", e);
		}

		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new SmsException("不支持的编码", e);
		}

		StringBuilder result = new StringBuilder();
		try {
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			throw new SmsException("读取IO流失败", e);
		} catch (Exception e) {
			throw new SmsException("读取响应失败", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				//noinspection ThrowFromFinallyBlock
				throw new SmsException("关闭IO流失败", e);
			}
		}

		log.info("短信平台响应: " + result);

		if (!result.toString().contains("<result>0000</result>")) {
			throw new SmsException("短信发送失败");
		}
	}
}
