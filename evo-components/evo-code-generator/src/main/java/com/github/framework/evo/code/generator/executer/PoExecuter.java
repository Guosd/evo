package com.github.framework.evo.code.generator.executer;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.GenFileInfo;
import com.github.framework.evo.code.generator.freemarker.FreeMarkerUtils;
import com.github.framework.evo.code.generator.schema.Table;
import com.github.framework.evo.code.generator.util.GeneratorUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoExecuter extends TableBaseExecuter {
    private static final Logger log = LoggerFactory.getLogger(PoExecuter.class);
    private GenFileInfo poInfo;
    private Configuration cfg;
    private static final String CONTAINS_DATE = "containsDate";
    private static final String CONTAINS_BIGDECIMAL = "containsBigDecimal";

    public PoExecuter(Configuration cfg, GenConfig genConfig, GenFileInfo poInfo) {
        super(genConfig);
        this.cfg = cfg;
        this.poInfo = poInfo;
    }

    public void build(Table table) throws IOException {
        Map<Object, Object> root = new HashMap();
        root.put("poInfo", this.poInfo);
        root.put("table", table);
        FreeMarkerUtils.registMethod(root);
        if (table.getPrimaryKeys().size() == 1) {
            this.buildPo(root, table);
        } else {
            this.buildUnionKeyPo(root, table);
            this.buildUnionPo(root, table);
        }

    }

    public void buildPo(Map<Object, Object> root, Table table) throws IOException {
        File targetFile = new File(this.poInfo.getPath(), this.poInfo.getName() + ".java");
        List<String> types = GeneratorUtils.getTableColumnTypes(table);
        root.put("containsDate", GeneratorUtils.containsDate(types));
        root.put("containsBigDecimal", GeneratorUtils.containsBigDecimal(types));
        this.buildFile(root, targetFile, "po.ftlh", "PO");
    }

    public void buildUnionKeyPo(Map<Object, Object> root, Table table) throws IOException {
        String keyPoName = this.poInfo.getName() + "Key";
        File targetFile = new File(this.poInfo.getPath(), keyPoName + ".java");
        List<String> types = GeneratorUtils.getTablePrimaryKeyTypes(table);
        root.put("containsDate", GeneratorUtils.containsDate(types));
        root.put("containsBigDecimal", GeneratorUtils.containsBigDecimal(types));
        this.buildFile(root, targetFile, "unionkeypo.ftlh", "Union PO Key");
    }

    public void buildUnionPo(Map<Object, Object> root, Table table) throws IOException {
        File targetFile = new File(this.poInfo.getPath(), this.poInfo.getName() + ".java");
        List<String> types = GeneratorUtils.getTableColumnTypes(table);
        root.put("containsDate", GeneratorUtils.containsDate(types));
        root.put("containsBigDecimal", GeneratorUtils.containsBigDecimal(types));
        this.buildFile(root, targetFile, "unionpo.ftlh", "Union PO");
    }

    public void buildFile(Map<Object, Object> root, File targetFile, String ftlhFileName, String desc) throws IOException {
        try {
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(targetFile.toURI()), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            Throwable var6 = null;

            try {
                Template template = this.cfg.getTemplate(ftlhFileName);
                template.process(root, bw);
                log.info("Generate {} file {}", desc, targetFile.getAbsolutePath());
            } catch (Throwable var16) {
                var6 = var16;
                throw var16;
            } finally {
                if (bw != null) {
                    if (var6 != null) {
                        try {
                            bw.close();
                        } catch (Throwable var15) {
                            var6.addSuppressed(var15);
                        }
                    } else {
                        bw.close();
                    }
                }

            }
        } catch (TemplateException var18) {
            log.warn("{}", var18.getMessage(), var18);
        }

    }
}