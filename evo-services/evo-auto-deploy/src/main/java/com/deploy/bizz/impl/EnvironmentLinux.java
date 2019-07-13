package com.deploy.bizz.impl;

import com.github.framework.evo.deploy.bizz.EnvironmentCheck;
import com.github.framework.evo.deploy.util.FormatPrint;
import com.github.framework.evo.deploy.util.impl.LinuxCommand;
import com.github.framework.evo.deploy.util.impl.WindowsCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnvironmentLinux implements EnvironmentCheck {
    @Autowired
    private LinuxCommand linuxCommand;
    @Autowired
    private FormatPrint formatPrint;

    public void checkMvn() {
        String result = linuxCommand.execute("mvn --version");
        formatPrint.print("linux mvn"+result);
    }

    public void checkJdk() {
        String result = linuxCommand.execute("java -version");
        formatPrint.print("linux jdk:"+result);
    }

    public void checkSvn() {
        String result = linuxCommand.execute("svn -version");
        formatPrint.print("linux svn"+result);
    }
}
