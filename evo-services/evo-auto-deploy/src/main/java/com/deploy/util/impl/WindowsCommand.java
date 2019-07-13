package com.deploy.util.impl;

import com.github.framework.evo.deploy.util.SystemCommand;
import io.cloudsoft.winrm4j.client.WinRmClientContext;
import io.cloudsoft.winrm4j.winrm.WinRmTool;
import io.cloudsoft.winrm4j.winrm.WinRmToolResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.AuthSchemes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WindowsCommand implements SystemCommand {
    @Value("${windows.host}")
    private String host ;
    @Value("${windows.username}")
    private String username;
    @Value("${windows.password}")
    private String password ;

    WinRmTool.Builder builder;
    WinRmClientContext context;

    @Override
    public String execute(String command) {
        if(builder != null){
            WinRmTool tool =  builder.build();
            tool.setOperationTimeout(180000L);
            WinRmToolResponse resp = tool.executeCommand(command);
            String out = resp.getStdOut();
            return out;
        }else {
            getBuild();
            return execute(command);
        }
    }

    private void getBuild(){
        context = WinRmClientContext.newInstance();
        builder = WinRmTool.Builder.builder(host, username, password);
        builder.authenticationScheme(AuthSchemes.NTLM);
        builder.port(5985);
        builder.useHttps(false);

        builder.context(context);
    }

    private void close(){
        context.shutdown();
    }

}
