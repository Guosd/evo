package com.github.framework.evo.code.generator.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public final class WordFileUtils {
    private static final Logger log = LoggerFactory.getLogger(WordFileUtils.class);
    public static final String WORD_FILE_HOME = "wordfile.home";
    public static final String WORD_ROOT_FILE = "wordroot.properties";
    public static final String WORD_ROOT_TABLE_FILE = "wordroot-table.properties";
    private static Map<String, Word> wordMap = new HashMap();
    private static boolean hasShowHelp;
    private static final int MAX_WORDROOT_FILES = 9;
    private static final String UTF8 = "UTF-8";

    private WordFileUtils() {
    }

    public static synchronized void initWordMap() {
        String wordfileHome = System.getProperty("wordfile.home", "");
        if (!hasShowHelp) {
            log.info("**************************************************************");
            log.info("**开始加载词根表，词根优先级如下： ");
            log.info("**1：以下划线分隔的命名");
            log.info("**2：自带标准词根表(wordroot.properties)");
            log.info("**3：自带标准词根表业务表(wordroot-table.properties)");
            log.info("**4：环境变量wordfile.home(\"" + wordfileHome + "\")下自定义词根表文件(wordfile9.properties->wordfile9.properties)");
            log.info("**5：CLASSPATH下自定义词根表文件(wordfile9.properties->wordfile9.properties)");
            log.info("**6：数据库中的大小写");
            log.info("**7：全部改成小写");
            log.info("**************************************************************");
            hasShowHelp = true;
        }

        try {
            int i;
            if (wordfileHome != null && !"".equals(wordfileHome)) {
                for(i = 9; i >= 1; --i) {
                    init(wordfileHome, "wordfile" + i + ".properties");
                }
            }

            for(i = 9; i >= 1; --i) {
                init("wordfile" + i + ".properties");
            }

            init("wordroot-table.properties");
            init("wordroot.properties");
        } catch (IOException var2) {
            log.warn("{}", var2);
        }

    }

    private static String getClassNameWithoutPackage(Class<?> cl) {
        String className = cl.getName();
        int pos = className.lastIndexOf(46) + 1;
        if (pos == -1) {
            pos = 0;
        }

        return className.substring(pos);
    }

    public static String getRealPathName(Class<?> cl) {
        URL url = cl.getResource(getClassNameWithoutPackage(cl) + ".class");
        return url != null ? url.getPath() : null;
    }

    private static void init(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        if (file.exists() && file.canRead()) {
            InputStream is = new FileInputStream(file);
            Throwable var4 = null;

            try {
                log.info("Load wordfile " + fileName);
                PropertyResourceBundle bundle = new PropertyResourceBundle(is);
                Enumeration ration = bundle.getKeys();

                while(ration.hasMoreElements()) {
                    String objKey = (String)ration.nextElement();
                    if (objKey != null) {
                        String key = objKey.toLowerCase(Locale.getDefault());
                        String value = bundle.getString(objKey);
                        if (value != null && !"".equals(value)) {
                            value = new String(value.getBytes("UTF-8"), Charset.defaultCharset());
                            Word word = new Word(objKey, value);
                            wordMap.put(key, word);
                        }
                    }
                }
            } catch (Throwable var19) {
                var4 = var19;
                throw var19;
            } finally {
                if (is != null) {
                    if (var4 != null) {
                        try {
                            is.close();
                        } catch (Throwable var18) {
                            var4.addSuppressed(var18);
                        }
                    } else {
                        is.close();
                    }
                }

            }
        } else {
            log.info("Skip wordfile " + file.getAbsolutePath());
        }

    }

    private static void init(String fileName) throws IOException {
        InputStream is = WordFileUtils.class.getClassLoader().getResourceAsStream(fileName);
        Throwable var2 = null;

        try {
            if (is != null) {
                log.info("Load wordfile " + fileName);
                PropertyResourceBundle bundle = new PropertyResourceBundle(is);
                Enumeration ration = bundle.getKeys();

                while(ration.hasMoreElements()) {
                    String objKey = (String)ration.nextElement();
                    if (objKey != null) {
                        String key = objKey.toLowerCase(Locale.getDefault());
                        String value = bundle.getString(objKey);
                        if (value != null && "".equals(value)) {
                            value = new String(value.getBytes("UTF-8"), Charset.defaultCharset());
                            Word word = new Word(objKey, value);
                            wordMap.put(key, word);
                        }
                    }
                }

                return;
            }

            log.info("Skip wordfile " + fileName);
        } catch (Throwable var18) {
            var2 = var18;
            throw var18;
        } finally {
            if (is != null) {
                if (var2 != null) {
                    try {
                        is.close();
                    } catch (Throwable var17) {
                        var2.addSuppressed(var17);
                    }
                } else {
                    is.close();
                }
            }

        }

    }

    public static String getBeautyObjectName(String name) {
        if (name != null && !name.isEmpty()) {
            String key = name.toLowerCase(Locale.getDefault());
            key = key.replace("_", "");
            String beautyName = null;
            if (name.contains("_")) {
                StringBuilder sb = new StringBuilder();
                String[] fields = name.split("_");
                sb.append(fields[0].toLowerCase(Locale.getDefault()));

                for(int i = 1; i < fields.length; ++i) {
                    String temp = fields[i];
                    sb.append(temp.substring(0, 1).toUpperCase(Locale.getDefault()));
                    sb.append(temp.substring(1).toLowerCase(Locale.getDefault()));
                }

                beautyName = sb.toString();
            } else if (wordMap.containsKey(key)) {
                beautyName = ((Word)wordMap.get(key)).getName();
                beautyName = upperCaseFirstChar(beautyName);
            } else if (!name.toUpperCase(Locale.US).equals(name) && !name.toLowerCase(Locale.US).equals(name)) {
                beautyName = name;
            } else {
                beautyName = name.toLowerCase(Locale.getDefault());
            }

            beautyName = upperCaseFirstChar(beautyName);
            return beautyName;
        } else {
            throw new IllegalArgumentException("name must have value.");
        }
    }

    public static String getBeautyInstanceName(String name) {
        String beautyName = getBeautyObjectName(name);
        if (beautyName.length() <= 1 || !Character.isUpperCase(beautyName.charAt(1))) {
            beautyName = lowerCaseFirstChar(beautyName);
        }

        return beautyName;
    }

    public static String getBeautyDesc(String name) {
        String beautyDesc = "";
        String key = name.toLowerCase(Locale.getDefault());
        key = key.replace("_", "");
        if (wordMap.containsKey(key)) {
            beautyDesc = ((Word)wordMap.get(key)).getDesc();
        } else {
            beautyDesc = name;
        }

        return beautyDesc;
    }

    public static String lowerCaseFirstChar(String iString) {
        String newString = iString.substring(0, 1).toLowerCase(Locale.getDefault()) + iString.substring(1);
        return newString;
    }

    public static String upperCaseFirstChar(String iString) {
        String newString = iString.substring(0, 1).toUpperCase(Locale.getDefault()) + iString.substring(1);
        return newString;
    }

    public static void printString(String value) {
        try {
            String[] codes = new String[]{"GBK", "ISO8859-1", "UTF-8"};

            int i;
            for(i = 0; i < codes.length; ++i) {
                for(int j = 0; j < codes.length; ++j) {
                    if (i != j) {
                        log.info(codes[i] + "--" + codes[j] + "====" + new String(value.getBytes(codes[i]), codes[j]));
                    }
                }
            }

            for(i = 0; i < codes.length; ++i) {
                log.info(codes[i] + "====" + new String(value.getBytes(codes[i]), Charset.defaultCharset()));
            }

            for(i = 0; i < codes.length; ++i) {
                log.info(codes[i] + "==----==" + new String(value.getBytes(Charset.defaultCharset()), codes[i]));
            }
        } catch (Exception var4) {
            log.warn("{}", var4);
        }

    }
}
