package com.deploy.util.impl;

import com.github.framework.evo.deploy.util.SystemCommand;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
@Component
public class LinuxCommand implements SystemCommand {
    @Value("${linux.host}")
    private String host;
    @Value("${linux.port}")
    private int port;
    @Value("${linux.username}")
    private String username;
    @Value("${linux.password}")
    private String password;

    private Session session = null;

    @Override
    public String execute(String command) {
        String result = "";
        try {
            getSession();
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);         //添加传入进来的shell命令
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);//通道连接错误信息提示
            channelExec.connect();
            log.info("start execute channel command!  {}",command);
            BufferedReader in = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
            String msg;
            while ((msg = in.readLine()) != null) {
                log.info(msg);
                result += msg+"\n";
            }
            in.close();
            channelExec.disconnect();
            log.info("end execute channel command!");
        }catch (Exception e){
            log.error("execute {} encurrent some exception",e.getCause());
        }
        return result;
    }

    public Session getConnect(String userName,String password,String ip,int port) {
        if(session == null || (session != null && !session.isConnected())){
            try {
                JSch jSch = new JSch();
                session = jSch.getSession(userName,ip,port);
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setTimeout(1000);
                session.connect();
                return session;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return session;
        }
    }

    public Session getSession(){
        if(session != null && session.isConnected()){
            return session;
        }else{
            log.info("connect to {}:{} {}",host,port,username);
            return getConnect(username,password,host,port);
        }
    }
}
