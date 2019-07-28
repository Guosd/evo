package com.github.framework.evo.autodeploy.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.ReusableMessage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@Slf4j
public class StreamUtil {

    public static String getString(InputStream inputStream){
        String result = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(System.getProperty("sun.jnu.encoding"))));
            String line = null;
            while((line = bufferedReader.readLine())!=null) {
                result += line+"\n";
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result;
    }
}
