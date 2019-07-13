package com.deploy.bizz;

import com.github.framework.evo.deploy.bizz.impl.EnvironmentLinux;
import com.github.framework.evo.deploy.bizz.impl.EnvironmentWindows;
import com.github.framework.evo.deploy.entity.DeployRequestVo;
import com.github.framework.evo.deploy.util.SystemCommand;
import com.github.framework.evo.deploy.util.impl.LinuxCommand;
import com.github.framework.evo.deploy.util.impl.WindowsCommand;
import com.jcraft.jsch.Session;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

@Slf4j
@Service
public class AutoDeployBizz {
    @Autowired
    private LinuxCommand linuxCommand;
    @Autowired
    private WindowsCommand windowsCommand;
    @Autowired
    private EnvironmentWindows environmentWindows;
    @Autowired
    private EnvironmentLinux environmentLinux;

    public String deploy(DeployRequestVo deployRequestVo){
        //checkEnvirement();
        windowsCommand.execute("mkdir svntmp");
        windowsCommand.execute("cd svntmp");

        windowsCommand.execute("svn checkout svn://10.128.23.213:13690/Microservice_SystemBaseline/ydpic-car/2.INT_ydpic-car/ydpic-car");



        String result = linuxCommand.execute(deployRequestVo.getEnvirement());

        return "success";
    }

    private void checkEnvirement() {
        environmentWindows.checkSvn();
        environmentWindows.checkMvn();
        environmentWindows.checkJdk();
        environmentLinux.checkSvn();
        environmentLinux.checkMvn();
        environmentLinux.checkJdk();
    }


}
