package com.ritoinfo.framework.evo.common.uitl;

import com.ritoinfo.framework.evo.common.exception.NetOperateException;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * User: Kyll
 * Date: 2018-04-19 16:16
 */
@Slf4j
public class NetUtil {
	public InetAddress getLocalHostLANAddress() {
		InetAddress candidateAddress = null;
		// 遍历所有的网络接口
		try {
			for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
				NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
				// 在所有的接口下再遍历IP
				for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
					InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
					if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
						if (inetAddr.isSiteLocalAddress()) {
							// 如果是site-local地址，就是它了
							return inetAddr;
						} else if (candidateAddress == null) {
							// site-local类型的地址未被发现，先记录候选地址
							candidateAddress = inetAddr;
						}
					}
				}
			}
		} catch (SocketException e) {
			throw new NetOperateException("获取 NetworkInterfaces 失败");
		}

		if (candidateAddress != null) {
			return candidateAddress;
		}

		// 如果没有发现 non-loopback地址.只能用最次选的方案
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new NetOperateException("获取 Local Host 失败");
		}
	}


}
