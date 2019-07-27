package com.github.framework.evo.autodeploy.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 获取子层文件
     * @param path
     * @param fileName
     * @return
     */
    public static List<String> findSubFile(String path,String fileName){
        List<String> list = new ArrayList<>();
        findFile(path,list,fileName,true);
        return list;
    }

    /**
     * 获取子层文件
     * @param path
     * @param fileName
     * @return
     */
    public static List<String> findSubFile(String path,String fileName,boolean flag){
        List<String> list = new ArrayList<>();
        findFile(path,list,fileName,flag);
        return list;
    }

    /**
     * 递归的寻找子层文件。
     * @param path
     * @param list
     * @param fileName
     */
    private static void findFile(String path,List<String> list,String fileName,boolean flag) {
        File file = new File(path);
        if(file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                String absoluteFile = listFiles[i].getAbsolutePath();
                findFile(absoluteFile,list,fileName,flag);
            }
        }else {
            if(file.getName().equals(fileName) && flag) {
                list.add(file.getAbsolutePath());
            }
            if(file.getName().endsWith(fileName) && !flag) {
                list.add(file.getAbsolutePath());
            }
        }
    }

    /**
     * 将原来的文件覆盖掉
     * @param content
     * @param path
     * @throws IOException
     */
    public static void replaceFile(String content,String path) throws IOException {
        File file = new File(path);
        if(file.exists()){
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            fw.close();
        }
    }

}
