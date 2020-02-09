package com.github.framework.evo.autodeploy.util;



import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;

@Slf4j
public class LinuxUtil {
    private Session session;
    private ChannelSftp channelSftp;
    private String host;
    private int port;
    private String username;
    private String password;


    public LinuxUtil(String host,int port,String username,String password){
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        init();
    }

    public void init(){
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(this.username,this.host,this.port);
            session.setConfig("userauth.gssapi-with-mic", "no");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(this.password);
            session.connect();
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public Session getSession(){
        if(session == null){
            init();
            return getSession();
        }else{
            return session;
        }
    }

    /**
     * 上传文件
     */
    public void upload(String remotePath,String localFilePath){
        try {
            Session session = this.getSession();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(remotePath);
            File file = new File(localFilePath);
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            channelSftp.put(is, file.getName());
            channelSftp.disconnect();
            is.close();
            log.info("upload file success!");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 执行命令，并返回结果
     */
    public String execCommand(String command){
        String result = "";
        try {
            Session session = this.getSession();
            ChannelExec openChannel = (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            openChannel.connect();

            InputStream is = openChannel.getInputStream();
            InputStream es = openChannel.getErrStream();

            result += StreamUtil.getString(is);
            result += StreamUtil.getString(es);
            is.close();
            es.close();
            openChannel.disconnect();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 执行多条命令
     */
    public String execCommand(String... command){
        String result = "";
        try {
            Session session = this.getSession();
            ChannelShell openChannel = (ChannelShell) session.openChannel("shell");
            openChannel.setPty(true);
            openChannel.connect();

            OutputStream os = openChannel.getOutputStream();

            PrintWriter pw = new PrintWriter(os);
            for (String s : command) {
                pw.println(s);
            }
            pw.println("exit");
            pw.flush();

            InputStream is = openChannel.getInputStream();
            result += StreamUtil.getString(is);

            is.close();
            openChannel.disconnect();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 打印操作
     * @author Administrator
     */
    private class StreamThread1 extends Thread{
        BufferedReader bufferedReader ;
        public StreamThread1(InputStream is) {
            bufferedReader = new BufferedReader(new InputStreamReader(is,Charset.forName(System.getProperty("sun.jnu.encoding"))));
        }

        @Override
        public void run() {
            int b ;
            try {
                String line = null;
                while((line = bufferedReader.readLine())!=null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            };
        }
    }

    public void close(){
        this.session.disconnect();
    }

    public static void main(String[] args) {
        LinuxUtil linuxUtil = new LinuxUtil("10.128.23.215",10022,"weblogic","ydcx$111");
        String s1 = linuxUtil.execCommand("cd /etc","pwd");
        System.out.println(s1);
        linuxUtil.close();
    }
}
