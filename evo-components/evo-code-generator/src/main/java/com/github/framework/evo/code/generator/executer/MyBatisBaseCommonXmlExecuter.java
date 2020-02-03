package com.github.framework.evo.code.generator.executer;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.freemarker.FreeMarkerUtils;
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

public class MyBatisBaseCommonXmlExecuter extends CommonBaseExecuter {
    private static final Logger log = LoggerFactory.getLogger(MyBatisBaseCommonXmlExecuter.class);
    private Configuration cfg;

    public MyBatisBaseCommonXmlExecuter(Configuration cfg, GenConfig genConfig) {
        super(genConfig);
        this.cfg = cfg;
    }

    public void build() throws IOException {
        File targetFile = new File(this.genConfig.getSaveDirForXml(), "base/CommonBaseDao.xml");
        Map<Object, Object> root = new HashMap();
        root.put("genConfig", this.genConfig);
        FreeMarkerUtils.registMethod(root);

        try {
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(targetFile.toURI()), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            Throwable var4 = null;

            try {
                Template template = this.cfg.getTemplate("mybatis-base-common-mapper.ftlh");
                template.process(root, bw);
                log.info("Generate mybatis base common mapper file {}", targetFile.getAbsolutePath());
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (bw != null) {
                    if (var4 != null) {
                        try {
                            bw.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        bw.close();
                    }
                }

            }
        } catch (TemplateException var16) {
            log.warn("{}", var16.getMessage(), var16);
        }

    }
}
