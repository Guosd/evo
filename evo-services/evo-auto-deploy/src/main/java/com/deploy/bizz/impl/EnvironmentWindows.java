package com.deploy.bizz.impl;

import com.github.framework.evo.deploy.bizz.EnvironmentCheck;
import com.github.framework.evo.deploy.util.FormatPrint;
import com.github.framework.evo.deploy.util.impl.WindowsCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnvironmentWindows implements EnvironmentCheck {
    @Autowired
    private WindowsCommand windowsCommand;
    @Autowired
    private FormatPrint formatPrint;

    public void checkMvn() {
        String result = windowsCommand.execute("mvn --version");
        formatPrint.print("windows mvn"+result);
    }

    public void checkJdk() {
        String result = windowsCommand.execute("java -version");
        formatPrint.print("windows jdk:"+result);
    }

    public void checkSvn() {
        String result = windowsCommand.execute("svn -version");
        formatPrint.print("windows svn"+result);
    }
}
