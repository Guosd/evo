package com.github.framework.evo.code.generator;


import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import com.github.framework.evo.code.generator.executer.DaoExecuter;
import com.github.framework.evo.code.generator.executer.MyBatisBaseCommonXmlExecuter;
import com.github.framework.evo.code.generator.executer.MyBatisBaseXmlExecuter;
import com.github.framework.evo.code.generator.executer.MyBatisCustomCommonXmlExecuter;
import com.github.framework.evo.code.generator.executer.MyBatisCustomXmlExecuter;
import com.github.framework.evo.code.generator.executer.PoExecuter;
import com.github.framework.evo.code.generator.executer.VoExecuter;
import com.github.framework.evo.code.generator.schema.Column;
import com.github.framework.evo.code.generator.schema.PrimaryKey;
import com.github.framework.evo.code.generator.schema.Table;
import com.github.framework.evo.code.generator.util.GeneratorUtils;
import java.io.IOException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generator extends BaseGenerator {
    private static final Logger log = LoggerFactory.getLogger(Generator.class);
    private GenFileInfo voInfo;
    private GenFileInfo poInfo;
    private GenFileInfo daoInfo;
    private GenFileInfo baseMapperXmlInfo;
    private GenFileInfo mapperXmlInfo;

    public Generator() {
    }

    private String assemblePackage(String module, String catalog) {
        String result = this.genConfig.getBasePackage() + "." + module;
        if (catalog != null && catalog.trim().length() > 0) {
            result = result + "." + catalog;
        }

        return result;
    }

    private static String assembleXmlPackage(String module) {
        String result = "";
        if (module != null && module.trim().length() > 0) {
            result = module;
        } else {
            result = "misc";
        }

        return result;
    }

    private void resetFileInfo(final String keyNameIn, String module) {
        String saveDir = this.genConfig.getSaveDir();
        String keyName = keyNameIn;
        String ignoreTablePrefix;
        if (this.genConfig.getIgnoreTablePrefixs() != null) {
            String[] var5 = this.genConfig.getIgnoreTablePrefixs();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                ignoreTablePrefix = var5[var7];
                ignoreTablePrefix = ignoreTablePrefix.replace("_", "");
                if (keyName.toLowerCase(Locale.getDefault()).startsWith(ignoreTablePrefix.toLowerCase(Locale.getDefault()))) {
                    keyName = keyName.substring(ignoreTablePrefix.length());
                    break;
                }
            }
        }

        String name = keyName + "Vo";
        String packageName = this.assemblePackage(module, "vo");
        String path = getFilePath(this.genConfig.getSaveDirForVo(), getPathFromPackageName(packageName));
        this.voInfo = new GenFileInfo(name, packageName, path, keyName);
        name = keyName;
        if (this.genConfig.keepPrefixForPO) {
            name = keyNameIn;
        }

        packageName = this.assemblePackage(module, "po");
        path = getFilePath(saveDir, getPathFromPackageName(packageName));
        this.poInfo = new GenFileInfo(name, packageName, path, keyName);
        name = keyName + "Dao";
        packageName = this.assemblePackage(module, "dao");
        path = getFilePath(saveDir, getPathFromPackageName(packageName));
        this.daoInfo = new GenFileInfo(name, packageName, path, keyName);
        name = keyName + "BaseDao";
        packageName = assembleXmlPackage(module);
        ignoreTablePrefix = getFilePath(this.genConfig.getSaveDirForXml(), "base");
        path = getFilePath(ignoreTablePrefix, getPathFromPackageName(packageName));
        this.baseMapperXmlInfo = new GenFileInfo(name, packageName, path, keyName);
        name = keyName + "Dao";
        packageName = assembleXmlPackage(module);
        ignoreTablePrefix = getFilePath(this.genConfig.getSaveDirForXml(), "custom");
        path = getFilePath(ignoreTablePrefix, getPathFromPackageName(packageName));
        this.mapperXmlInfo = new GenFileInfo(name, packageName, path, keyName);
    }

    @Override
    protected void run(Table table, String module) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setTemplateLoader(new ClassTemplateLoader(Generator.class, "/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        log.info("============处理表" + table.getName() + "==================");
        if (table.getPrimaryKeys().isEmpty()) {
            if (!this.genConfig.isUseFirstCloumnAsPK() || table.getColumns().isEmpty()) {
                log.info("表{}没有主键字段，忽略生成，请手工编写.", table.getName());
                return;
            }

            Column firstColumn = (Column)table.getColumns().get(0);
            PrimaryKey pk = new PrimaryKey();
            pk.setColumnName(firstColumn.getName());
            pk.setKeySeq(1);
            pk.setPkName(firstColumn.getName());
            table.getPrimaryKeys().add(pk);
            log.info("表{}没有主键字段，使用第一个字段作为主键.", table.getName(), ((Column)table.getColumns().get(0)).getName());
        }

        String beanName = GeneratorUtils.getObjectName(table.getName());
        this.resetFileInfo(beanName, module);
        this.fileOvervide = false;
        if (this.containsGenType(GenType.VO) && this.validFile(this.voInfo.getPath(), this.voInfo.getName(), ".java")) {
            (new VoExecuter(cfg, this.genConfig, this.voInfo)).build(table);
        }

        this.fileOvervide = true;
        if (this.containsGenType(GenType.PO) && this.validFile(this.poInfo.getPath(), this.poInfo.getName(), ".java")) {
            (new PoExecuter(cfg, this.genConfig, this.poInfo)).build(table);
        }

        this.fileOvervide = false;
        if (this.containsGenType(GenType.DAO) && this.validFile(this.daoInfo.getPath(), this.daoInfo.getName(), ".java")) {
            (new DaoExecuter(cfg, this.genConfig, this.daoInfo, this.poInfo)).build(table);
        }

        this.fileOvervide = true;
        if (this.containsGenType(GenType.BASE_MAPPER_XML) && this.validFile(this.baseMapperXmlInfo.getPath(), this.baseMapperXmlInfo.getName(), ".xml")) {
            (new MyBatisBaseXmlExecuter(cfg, this.genConfig, this.baseMapperXmlInfo, this.daoInfo, this.poInfo)).build(table);
        }

        this.fileOvervide = false;
        if (this.containsGenType(GenType.MAPPER_XML) && this.validFile(this.mapperXmlInfo.getPath(), this.mapperXmlInfo.getName(), ".xml")) {
            (new MyBatisCustomXmlExecuter(cfg, this.genConfig, this.mapperXmlInfo, this.daoInfo)).build(table);
        }

    }

    @Override
    protected void runCommon() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setTemplateLoader(new ClassTemplateLoader(Generator.class, "/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        this.fileOvervide = true;
        if (this.containsGenType(GenType.BASE_MAPPER_XML) && this.validFile(this.genConfig.getSaveDirForXml(), "base/common", ".xml")) {
            (new MyBatisBaseCommonXmlExecuter(cfg, this.genConfig)).build();
        }

        this.fileOvervide = false;
        if (this.containsGenType(GenType.MAPPER_XML) && this.validFile(this.genConfig.getSaveDirForXml(), "common/common", ".xml")) {
            (new MyBatisCustomCommonXmlExecuter(cfg, this.genConfig)).build();
        }

    }
}