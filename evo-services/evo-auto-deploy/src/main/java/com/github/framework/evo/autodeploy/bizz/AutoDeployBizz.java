package com.github.framework.evo.autodeploy.bizz;

import com.github.framework.evo.autodeploy.entity.*;
import com.github.framework.evo.autodeploy.util.FileUtil;
import com.github.framework.evo.autodeploy.util.LinuxUtil;
import com.github.framework.evo.autodeploy.util.WindowsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
@Service
@Slf4j
public class AutoDeployBizz {
    @Autowired
    private AutoDeployLogBizz autoDeployLogBizz;
    @Autowired
    private AutoDeployParameterBizz autoDeployParameterBizz;
    @Autowired
    private AutoReplaceParameterBizz autoReplaceParameterBizz;

    public AutoDeployResponseVo autoDeploy(AutoDeployRequestVo autoDeployRequestVo){
        RuntimeParameter runtimeParameter = new RuntimeParameter();
        AutoDeployResponseVo autoDeployResponseVo = null;
        checkOutFromSvn(autoDeployRequestVo,runtimeParameter);
        watting("Checked out revision",3);
        //找到文件并且将文件修改成想要的形式。
        findFile(autoDeployRequestVo,runtimeParameter);
        mvnPackage(autoDeployRequestVo,runtimeParameter);
        watting("BUILD SUCCESS",7);

        //移动到根目录
        copyJar2Root(runtimeParameter);
        watting();

        //上传linux服务器
        linuxOperation(runtimeParameter,autoDeployRequestVo);

        List<String> result = WindowsUtil.getResult();
        result.clear();
        return autoDeployResponseVo;
    }


    /**
     * 根据所传projectName 进行上传
     * @param runtimeParameter
     */
    private void linuxOperation(RuntimeParameter runtimeParameter,AutoDeployRequestVo autoDeployRequestVo){
        AutoDeployLog autoDeployLog = getAutoDeployLog();
        String[] projects = autoDeployRequestVo.getProjectName();
        List<String> jars = runtimeParameter.getJarPaths();
        for (int i = 0; i < projects.length; i++) {
            for (String path : jars) {
                if(path.contains(projects[i])){
                    log.info("上传jar包路径{}",path);
                    String host = autoDeployParameterBizz.findByKey(autoDeployRequestVo.getEnvirement()+"."+projects[i]+".upload.host");
                    String username = autoDeployParameterBizz.findByKey(autoDeployRequestVo.getEnvirement()+"."+projects[i]+".upload.username");
                    String password = autoDeployParameterBizz.findByKey(autoDeployRequestVo.getEnvirement()+"."+projects[i]+".upload.password");
                    String port = autoDeployParameterBizz.findByKey(autoDeployRequestVo.getEnvirement()+"."+projects[i]+".upload.port");
                    String remotePath = autoDeployParameterBizz.findByKey(autoDeployRequestVo.getEnvirement()+"."+projects[i]+".upload.directory");
                    String jarName = path.substring(path.lastIndexOf(File.separator)+1);
                    LinuxUtil linuxUtil = new LinuxUtil(host,Integer.parseInt(port),username,password);
                    processOperation(linuxUtil,remotePath,jarName,autoDeployLog,path);
                    linuxUtil.close();
                }
            }
        }
    }

    public void  processOperation(LinuxUtil linuxUtil,String remotePath,String jarName,AutoDeployLog autoDeployLog,String path){

        String result = linuxUtil.execCommand("ls -l " +remotePath);
        //检查有没有bak文件夹
        if(result.contains("No such file or directory")){
            log.info("配置錯誤，沒有指定文件夾！系统自动创建："+ remotePath);
        }
        if(!checkLinuxDirectory(result)){
            String command = "mkdir -p "+remotePath+"/"+"bak";
            linuxUtil.execCommand(command);
            autoDeployLog.setCommand(command);
            autoDeployLogBizz.insert(autoDeployLog);
        }else{

            String command = "ls "+remotePath+"/"+jarName;
            result = linuxUtil.execCommand(command);
            autoDeployLog.setCommand(command);
            autoDeployLogBizz.insert(autoDeployLog);
            if(!result.contains("No such file or directory")){
                //移动到bak 文件夹
                command = "mv "+ remotePath + "/" + jarName +" "+remotePath+"/bak";
                linuxUtil.execCommand(command);
                autoDeployLog.setCommand(command);
                autoDeployLogBizz.insert(autoDeployLog);
            }
        }

        //上传文件
        log.info("Linux 服务器路径：{}",remotePath);
        linuxUtil.upload(remotePath,path);

        //杀掉进程
        String command = "kill -9 `ps -ef | grep "+jarName+" | grep -v grep | awk '{print $2}'`";
        result = linuxUtil.execCommand(command);
        log.info(result);
        autoDeployLog.setCommand(command);
        autoDeployLogBizz.insert(autoDeployLog);

        String command1 = "cd "+remotePath;
        String command2 = "nohup java -jar "+jarName+" &";
        result = linuxUtil.execCommand(command1,command2);
        log.info(result);
        autoDeployLog.setCommand(command);
        autoDeployLogBizz.insert(autoDeployLog);
    }

    public boolean checkLinuxDirectory(String result){
        boolean check = false;
        String[] lines = result.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if(lines[i].contains("No such file or directory")){
                return false;
            }else if(lines[i].startsWith("d") && lines[i].endsWith("bak")){
                return true;
            }
        }
        return check;
    }
    /**
     * 移动jar包放到顶级目录
     * 这个是为了升级外网的时候方便，所以移动一下。
     */
    private void copyJar2Root(RuntimeParameter runtimeParameter) {
        AutoDeployLog autoDeployLog = getAutoDeployLog();

        WindowsUtil.execute(runtimeParameter.getRootPath());

        WindowsUtil.execute("cd " + runtimeParameter.getDirectory());
        WindowsUtil.execute("cd .." );
        List<String> pathList = FileUtil.findSubFile(runtimeParameter.getProjectPath(),".jar",false);
        for (String path : pathList) {
            if(path.contains("services")){
                String tempJarPath = runtimeParameter.getJarTempPath()+File.separator+path.substring(path.lastIndexOf(File.separator)+1);
                File file = new File(tempJarPath);
                if(file.exists()){
                    log.info("delete file: {}",tempJarPath);
                    file.delete();
                }
                runtimeParameter.getJarPaths().add(path);
                String command = "copy "+path +" "+runtimeParameter.getJarTempPath();
                WindowsUtil.execute(command);
                autoDeployLog.setCommand(command);
                autoDeployLogBizz.insert(autoDeployLog);
            }
        }
    }
    /**
     * 等待运行结束。
     * 循环等待，但是如果没有运行完，而且，三秒之内还没有输出，那就凉了
     */
    private void watting() {
        String message1 = "1";
        String message2 = "2";
        try {
            Thread.sleep(3000);
            while (!message1.equals(message2)){
                message1 = WindowsUtil.getMessage();
                Thread.sleep(10000);
                message2 = WindowsUtil.getMessage();
            }
        }catch (Exception e){
            log.error("睡眠失败？");
        }
    }

    private void watting(String message , int index) {
        try {
            List<String> result = WindowsUtil.getResult();
            while (result.size()<index || !result.get(result.size()-index).contains(message) ){
                Thread.sleep(1000);
            }
        }catch (Exception e){
            log.error("睡眠失败？{}",e.getMessage());
        }
    }

    private void mvnPackage(AutoDeployRequestVo autoDeployRequestVo,RuntimeParameter runtimeParameter){
        AutoDeployLog autoDeployLog = getAutoDeployLog();

        WindowsUtil.execute(runtimeParameter.getRootPath());
        autoDeployLog.setCommand(runtimeParameter.getRootPath());
        autoDeployLogBizz.insert(autoDeployLog);

        WindowsUtil.execute("cd " + runtimeParameter.getDirectory());
        autoDeployLog.setCommand("cd " + runtimeParameter.getDirectory());
        autoDeployLogBizz.insert(autoDeployLog);

        WindowsUtil.execute("mvn package");
        autoDeployLog.setCommand("mvn package");
        autoDeployLogBizz.insert(autoDeployLog);
    }

    /**
     * 寻找要修改的文件
     * @param autoDeployRequestVo
     * @param runtimeParameter
     */
    private void findFile(AutoDeployRequestVo autoDeployRequestVo,RuntimeParameter runtimeParameter){
        List<String> fileNames =  autoReplaceParameterBizz.findFileName();
        for (String fileName : fileNames) {
            List<String> filePath = FileUtil.findSubFile(runtimeParameter.getProjectPath(),fileName);
            for (String path : filePath) {
                modifyFile(fileName, path,autoDeployRequestVo);
            }
        }
    }

    /**
     * 修改文件
     * @param fileName
     * @param path
     */
    private void modifyFile(String fileName,String path,AutoDeployRequestVo autoDeployRequestVo) {
        List<AutoReplaceParameter> param =  autoReplaceParameterBizz.findReplaceParam(fileName,autoDeployRequestVo.getEnvirement());
        File file = new File(path);
        String fileContent = "";
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = test(line,param,path);
                fileContent += line+"\n";
            }

            FileUtil.replaceFile(fileContent,path);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 处理每个属性，修改成特定值。
     * @param line
     * @param autoReplaceParameters
     * @param path
     * @return
     *  这个方法有点NB
     * 不断的将数据库中配置的key的第一个单词替换成"  " 来进行匹配值。
     */
    private String test(String line,List<AutoReplaceParameter> autoReplaceParameters,String path){
        for (int i =0;i<autoReplaceParameters.size();i++) {
            if(path.contains(autoReplaceParameters.get(i).getProjectName())) {  //判断是不是本项目的属性
                String key = autoReplaceParameters.get(i).getKey();
                String lineHead = line.substring(0, line.indexOf(":"));
                String head = "";
                if (key.contains(".")) {
                    head = key.substring(0, key.indexOf("."));
                    if (head.equals(lineHead)) {
                        key = key.replace(head.trim(), "  ");
                        key = key.replaceFirst("[.]", "");
                        autoReplaceParameters.get(i).setKey(key);
                    }
                } else {
                    head = key;
                    if (head.equals(lineHead)) {
                        line = line.substring(0, line.indexOf(":") + 2) + autoReplaceParameters.get(i).getNewvalue();
                        autoReplaceParameters.remove(i);
                        return line;
                    }
                }
            }
        }
        return line;
    }
    /**
     * 在svn上进行下载
     * @param autoDeployRequestVo 请求参数，需要获取环境参数，项目名称
     */
    private void checkOutFromSvn(AutoDeployRequestVo autoDeployRequestVo,RuntimeParameter runtimeParameter){
        //获取组织svn 的基本参数。
        String commandTemplate = autoDeployParameterBizz.findByKey("svn.command.template");
        String tempDirectory = autoDeployParameterBizz.findByKey("svn.tempdirectory");
        String username = autoDeployParameterBizz.findByKey("svn.username");
        String password = autoDeployParameterBizz.findByKey("svn.password");
        String envirement = autoDeployRequestVo.getEnvirement().toLowerCase();
        String parentName = autoDeployRequestVo.getParentName().toLowerCase();
        String svnUrl = autoDeployParameterBizz.findByKey(envirement+"."+parentName);
        commandTemplate = commandTemplate.replace("#{svnurl}", svnUrl);
        commandTemplate = commandTemplate.replace("#{tempdirectory}", tempDirectory+File.separator+envirement+ File.separator +parentName);
        commandTemplate = commandTemplate.replace("#{username}", username);
        commandTemplate = commandTemplate.replace("#{password}", password);
        runtimeParameter.setProjectPath(tempDirectory+File.separator+envirement+ File.separator +parentName);
        runtimeParameter.setRootPath(tempDirectory.substring(0,2));
        runtimeParameter.setJarTempPath(tempDirectory+File.separator+envirement);
        runtimeParameter.setDirectory(runtimeParameter.getProjectPath().substring(2,runtimeParameter.getProjectPath().length()));
        WindowsUtil.execute(commandTemplate);

        AutoDeployLog autoDeployLog = getAutoDeployLog();
        autoDeployLog.setCommand(commandTemplate);
        autoDeployLogBizz.insert(autoDeployLog);
    }

    private AutoDeployLog getAutoDeployLog(){
        AutoDeployLog autoDeployLog = new AutoDeployLog();
        autoDeployLog.setId(UUID.randomUUID().toString());
        autoDeployLog.setCommand("");
        autoDeployLog.setCreateBy("Guosd");
        autoDeployLog.setCreateTime(new Date());
        autoDeployLog.setUpdateBy("Guosd");
        autoDeployLog.setUpdateTime(new Date());
        return autoDeployLog;
    }
}
