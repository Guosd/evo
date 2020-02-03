package com.github.framework.evo.code.generator.executer;

import com.github.framework.evo.code.generator.GenFileInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.GenFileInfo;
import com.github.framework.evo.code.generator.freemarker.FreeMarkerUtils;
import com.github.framework.evo.code.generator.schema.Column;
import com.github.framework.evo.code.generator.schema.PrimaryKey;
import com.github.framework.evo.code.generator.schema.Table;
import com.github.framework.evo.code.generator.util.TypeUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoExecuter extends TableBaseExecuter {
    private static final Logger log = LoggerFactory.getLogger(DaoExecuter.class);
    private GenFileInfo daoInfo;
    private GenFileInfo poInfo;
    private Configuration cfg;

    public DaoExecuter(Configuration cfg, GenConfig genConfig, GenFileInfo daoInfo, GenFileInfo poInfo) {
        super(genConfig);
        this.cfg = cfg;
        this.daoInfo = daoInfo;
        this.poInfo = poInfo;
    }

    public void build(Table table) throws IOException {
        List<PrimaryKey> primaryKeys = table.getPrimaryKeys();
        String idType = "";
        boolean isUnionKey = false;
        if (primaryKeys.isEmpty()) {
            throw new IllegalArgumentException("不支持无主键的表");
        } else {
            if (primaryKeys.size() == 1) {
                String columnName = ((PrimaryKey)primaryKeys.get(0)).getColumnName();
                Iterator var6 = table.getKeyColumns().iterator();

                while(var6.hasNext()) {
                    Column column = (Column)var6.next();
                    if (column.getName().equalsIgnoreCase(columnName)) {
                        idType = TypeUtils.processType(column.getType());
                        break;
                    }
                }
            } else {
                isUnionKey = true;
                idType = this.poInfo.getName() + "Key";
            }

            File targetFile = new File(this.daoInfo.getPath(), this.daoInfo.getName() + ".java");
            Map<Object, Object> root = new HashMap();
            root.put("daoInfo", this.daoInfo);
            root.put("table", table);
            root.put("poInfo", this.poInfo);
            root.put("idType", idType);
            root.put("isUnionKey", isUnionKey);
            FreeMarkerUtils.registMethod(root);

            try {
                BufferedWriter bw = Files.newBufferedWriter(Paths.get(targetFile.toURI()), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
                Throwable var8 = null;

                try {
                    Template template = this.cfg.getTemplate("dao.ftlh");
                    template.process(root, bw);
                    log.info("Generate Dao file {}", targetFile.getAbsolutePath());
                } catch (Throwable var18) {
                    var8 = var18;
                    throw var18;
                } finally {
                    if (bw != null) {
                        if (var8 != null) {
                            try {
                                bw.close();
                            } catch (Throwable var17) {
                                var8.addSuppressed(var17);
                            }
                        } else {
                            bw.close();
                        }
                    }

                }
            } catch (TemplateException var20) {
                log.warn("{}", var20.getMessage(), var20);
            }

        }
    }
}
