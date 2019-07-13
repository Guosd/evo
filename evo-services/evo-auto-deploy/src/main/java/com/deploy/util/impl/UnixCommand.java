package com.deploy.util.impl;

import com.github.framework.evo.deploy.util.SystemCommand;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;

public class UnixCommand implements SystemCommand {
    @Value("${linux.host}")
    private String host;
    @Value("${linux.port}")
    private int port;
    @Value("${linux.username}")
    private String username;
    @Value("${linux.password}")
    private String password;

    private Session session;

    @Override
    public String execute(String command) {
        return null;
    }

}
