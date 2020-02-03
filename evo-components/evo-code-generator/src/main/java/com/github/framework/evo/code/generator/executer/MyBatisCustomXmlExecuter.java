package com.github.framework.evo.code.generator.executer;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.GenFileInfo;
import com.github.framework.evo.code.generator.freemarker.FreeMarkerUtils;
import com.github.framework.evo.code.generator.schema.Table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisCustomXmlExecuter extends TableBaseExecuter {
    private static final Logger log = LoggerFactory.getLogger(MyBatisCustomXmlExecuter.class);
    private GenFileInfo mapperXmlInfo;
    private GenFileInfo daoInfo;
    private Configuration cfg;

    public MyBatisCustomXmlExecuter(Configuration cfg, GenConfig genConfig, GenFileInfo mapperXmlInfo, GenFileInfo daoInfo) {
        super(genConfig);
        this.cfg = cfg;
        this.mapperXmlInfo = mapperXmlInfo;
        this.daoInfo = daoInfo;
    }

    public void build(Table table) throws IOException {
        File targetFile = new File(this.mapperXmlInfo.getPath(), this.mapperXmlInfo.getName() + ".xml");
        Map<Object, Object> root = new HashMap();
        root.put("genConfig", this.genConfig);
        root.put("daoInfo", this.daoInfo);
        root.put("table", table);
        FreeMarkerUtils.registMethod(root);

        try {
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(targetFile.toURI()), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            Throwable var5 = null;

            try {
                Template template = this.cfg.getTemplate("mybatis-custom-mapper.ftlh");
                template.process(root, bw);
                log.info("Generate mybatis custom mapper file {}", targetFile.getAbsolutePath());
            } catch (Throwable var15) {
                var5 = var15;
                throw var15;
            } finally {
                if (bw != null) {
                    if (var5 != null) {
                        try {
                            bw.close();
                        } catch (Throwable var14) {
                            var5.addSuppressed(var14);
                        }
                    } else {
                        bw.close();
                    }
                }

            }
        } catch (TemplateException var17) {
            log.warn("{}", var17.getMessage(), var17);
        }

    }
}
