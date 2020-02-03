package com.github.framework.evo.code.generator;


import com.github.framework.evo.code.generator.schema.Database;
import com.github.framework.evo.code.generator.schema.Table;
import com.github.framework.evo.code.generator.util.WordFileUtils;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class BaseGenerator {
    private static final Logger log = LoggerFactory.getLogger(BaseGenerator.class);
    protected static final String JAVA_SUFFIX = ".java";
    protected static final String XML_SUFFIX = ".xml";
    protected GenConfig genConfig;
    protected List<GenParam> paramList;
    protected Database database;
    protected boolean fileOvervide;

    BaseGenerator() {
    }

    protected abstract void run(Table table, String basePackage) throws IOException;

    protected abstract void runCommon() throws IOException;

    public void setGenConfig(GenConfig genConfig) {
        this.genConfig = genConfig;
    }

    public void setParamList(List<GenParam> paramList) {
        this.paramList = paramList;
    }

    protected static String getPathFromPackageName(String packageName) {
        return packageName != null && "".equals(packageName) ? "" : packageName.replace(".", File.separator);
    }

    protected static String getFilePath(String savePath, String segment) {
        File folder = new File(savePath, segment);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        return folder.getPath();
    }

    protected boolean containsGenType(GenType genType) {
        GenType[] var2 = this.genConfig.getGenTypes();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            GenType gen = var2[var4];
            if (gen == genType) {
                return true;
            }
        }

        return false;
    }

    public void generate() {
        WordFileUtils.initWordMap();
        if (!"".equals(this.genConfig.getInsertTimeForHisName())) {
            this.genConfig.setInsertTimeForHisName("insertTimeForHis");
        }

        this.genConfig.setInsertTimeForHisName(this.genConfig.getInsertTimeForHisName().toLowerCase(Locale.getDefault()).replace("_", ""));
        if (!"".equals(this.genConfig.getOperateTimeForHisName())) {
            this.genConfig.setOperateTimeForHisName("operateTimeForHis");
        }

        this.genConfig.setOperateTimeForHisName(this.genConfig.getOperateTimeForHisName().toLowerCase(Locale.getDefault()).replace("_", ""));
        if (!"".equals(this.genConfig.getVersionName())) {
            this.genConfig.setVersionName("Version");
        }

        this.genConfig.setVersionName(this.genConfig.getVersionName().toLowerCase(Locale.getDefault()).replace("_", ""));
        if (!"".equals(this.genConfig.getSaveDirForVo())) {
            this.genConfig.setSaveDirForVo(this.genConfig.getSaveDir());
        }

        if (!"".equals(this.genConfig.getSaveDirForXml())) {
            this.genConfig.setSaveDirForXml((new File(this.genConfig.getSaveDir(), "../resources/mapper")).getAbsolutePath());
        }

        if (this.containsGenType(GenType.VO) && !"".equals(this.genConfig.getSaveDirForVo())) {
            this.genConfig.setSaveDirForVo(this.genConfig.getSaveDir());
            log.info("未设置SaveDirForVo参数,使用默认位置");
        }

        if (this.containsGenType(GenType.MAPPER_XML) && "".equals(this.genConfig.getSaveDirForXml())) {
            throw new IllegalArgumentException("生成Mapper XML时需要设置SaveDirForXml参数");
        } else if (this.containsGenType(GenType.BASE_MAPPER_XML) && "".equals(this.genConfig.getSaveDirForXml())) {
            throw new IllegalArgumentException("生成Base Mapper XML时需要设置SaveDirForXml参数");
        } else {
            try {
                Class.forName(this.genConfig.getDbDriverName());
                Properties props = new Properties();
                props.setProperty("user", this.genConfig.getDbUser());
                props.setProperty("password", this.genConfig.getDbPassword());
                props.setProperty("remarks", "true");
                props.setProperty("useInformationSchema", "true");
                Connection conn = DriverManager.getConnection(this.genConfig.getDbUrl(), props);
                Throwable var3 = null;

                try {
                    DatabaseUtils databaseUtils = DatabaseUtils.getInstance(conn, this.genConfig.getDbCatalog(), this.genConfig.getDbSchema());
                    this.database = databaseUtils.getDatabase();
                    String dbName = this.database.getProductName().toLowerCase(Locale.getDefault());
                    if (!"".equals(this.genConfig.getDbSchema()) && dbName.indexOf("oracle") != -1) {
                        this.genConfig.setDbSchema(this.genConfig.getDbUser().toUpperCase(Locale.getDefault()));
                    }

                    Map<String, String> tableNamesMap = databaseUtils.getAllTableNamesMap();
                    if (tableNamesMap.size() != 0) {
                        Iterator var7 = this.paramList.iterator();

                        while(var7.hasNext()) {
                            GenParam genParam = (GenParam)var7.next();
                            String[] tableNames = genParam.getTables();

                            for(int i = 0; i < tableNames.length; ++i) {
                                String tableName = tableNames[i].toLowerCase(Locale.getDefault());
                                if (!tableNamesMap.containsKey(tableName)) {
                                    log.warn("Can't find table or view {}", tableName);
                                } else {
                                    Table table = databaseUtils.getTableInfo((String)tableNamesMap.get(tableName));
                                    this.run(table, genParam.getModule());
                                }
                            }
                        }

                        this.runCommon();
                        return;
                    }
                } catch (Throwable var22) {
                    var3 = var22;
                    throw var22;
                } finally {
                    if (conn != null) {
                        if (var3 != null) {
                            try {
                                conn.close();
                            } catch (Throwable var21) {
                                var3.addSuppressed(var21);
                            }
                        } else {
                            conn.close();
                        }
                    }

                }

            } catch (Exception var24) {
                log.warn("{}", var24);
            }
        }
    }

    protected boolean validFile(String dirPath, String beanName, String suffix) {
        File file = new File(dirPath, beanName + suffix);
        return !file.exists() || this.fileOvervide;
    }

    protected void openDir() {
        try {
            String osName = System.getProperty("os.name");
            if (osName != null) {
                if (osName.contains("Mac")) {
                    Runtime.getRuntime().exec("open " + this.genConfig.getSaveDir());
                } else if (osName.contains("Windows")) {
                    Runtime.getRuntime().exec("cmd /c start " + this.genConfig.getSaveDir());
                } else {
                    log.error("save dir:" + this.genConfig.getSaveDir());
                }
            }
        } catch (IOException var2) {
            log.warn("{}", var2);
        }

    }
}
