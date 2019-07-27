package com.github.framework.evo.autodeploy.util;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class LinuxUtil {
    private static Session session;
    private static ChannelSftp channelSftp;

    public static void upload() throws Exception {
        JSch jsch = new JSch();
        session = jsch.getSession("weblogic", "10.128.23.216", 10022);
        session.setPassword("ydcx$111");
        session.setConfig("userauth.gssapi-with-mic", "no");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        System.out.println("链接唱功？");

        channelSftp.ls("/home/weblogic");
        channelSftp.cd("/home/weblogic");
        File file = new File("E:\\234.txt");
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        channelSftp.put(is,file.getName());

    }

    public static void main(String[] args) throws Exception {
        LinuxUtil.upload();
    }


}
