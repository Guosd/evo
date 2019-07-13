package com.deploy.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FormatPrint {
    public static void print(String message){
        String s= "";
        for(int i =0; i<message.length();i++){
            s+="=";
        }
        log.info("======{}======",s);
        log.info("===   {}   ===",message);
        log.info("======{}======",s);
    }
}
