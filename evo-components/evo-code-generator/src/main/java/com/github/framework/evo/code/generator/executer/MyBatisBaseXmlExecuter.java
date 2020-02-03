package com.github.framework.evo.code.generator.executer;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.GenFileInfo;
import com.github.framework.evo.code.generator.freemarker.FreeMarkerUtils;
import com.github.framework.evo.code.generator.schema.Column;
import com.github.framework.evo.code.generator.schema.PrimaryKey;
import com.github.framework.evo.code.generator.schema.Table;
import com.github.framework.evo.code.generator.util.GeneratorUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisBaseXmlExecuter extends TableBaseExecuter {
    private static final Logger log = LoggerFactory.getLogger(MyBatisBaseXmlExecuter.class);
    private GenFileInfo baseMapperXmlInfo;
    private GenFileInfo daoInfo;
    private GenFileInfo poInfo;
    private Configuration cfg;

    public MyBatisBaseXmlExecuter(Configuration cfg, GenConfig genConfig, GenFileInfo baseMapperXmlInfo, GenFileInfo daoInfo, GenFileInfo poInfo) {
        super(genConfig);
        this.cfg = cfg;
        this.baseMapperXmlInfo = baseMapperXmlInfo;
        this.daoInfo = daoInfo;
        this.poInfo = poInfo;
    }

    public void build(Table table) throws IOException {
        List<PrimaryKey> primaryKeys = table.getPrimaryKeys();
        boolean isUnionKey = false;
        if (primaryKeys.isEmpty()) {
            throw new IllegalArgumentException("不支持无主键的表");
        } else {
            if (primaryKeys.size() == 1) {
                isUnionKey = false;
            } else {
                isUnionKey = true;
            }

            List<Column> updateColumns = new ArrayList();
            String[] ignoreColumnNamesWhenUpdate = this.genConfig.getIgnoreColumnNamesWhenUpdate();
            Set<String> ignoreColumnNamesWhenUpdateSet = new HashSet();
            String versionFieldName;
            if (ignoreColumnNamesWhenUpdate != null) {
                String[] var7 = ignoreColumnNamesWhenUpdate;
                int var8 = ignoreColumnNamesWhenUpdate.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    versionFieldName = var7[var9];
                    ignoreColumnNamesWhenUpdateSet.add(versionFieldName.toLowerCase(Locale.US));
                }
            }

            Iterator var27 = table.getNonKeyColumns().iterator();

            while(var27.hasNext()) {
                Column column = (Column)var27.next();
                String columnName = column.getName().toLowerCase(Locale.US);
                if (!ignoreColumnNamesWhenUpdateSet.contains(columnName)) {
                    updateColumns.add(column);
                }
            }

            PrimaryKey primaryKey = (PrimaryKey)primaryKeys.get(0);
            Map<Object, Object> root = new HashMap();
            root.put("genConfig", this.genConfig);
            root.put("daoInfo", this.daoInfo);
            root.put("poInfo", this.poInfo);
            root.put("table", table);
            root.put("updateColumns", updateColumns);
            root.put("primaryKey", primaryKey);
            root.put("isAutoIncrementPK", GeneratorUtils.isAutoIncrementPK(table));
            root.put("hasOperateTimeForHis", GeneratorUtils.hasColumn(table, this.genConfig.getOperateTimeForHisName()));
            boolean hasVersionField = false;
            versionFieldName = this.genConfig.getVersionName();
            Iterator var11 = table.getColumns().iterator();

            while(var11.hasNext()) {
                Column column = (Column)var11.next();
                if (column.getName().equalsIgnoreCase(this.genConfig.getVersionName())) {
                    hasVersionField = true;
                    versionFieldName = column.getName();
                    break;
                }
            }

            root.put("hasVersionField", hasVersionField);
            root.put("versionFieldName", versionFieldName);
            FreeMarkerUtils.registMethod(root);
            String tempFile = "mybatis-base-mapper.ftlh";
            if (isUnionKey) {
                tempFile = "mybatis-base-mapper-unionkey.ftlh";
            }

            File targetFile = new File(this.baseMapperXmlInfo.getPath(), this.baseMapperXmlInfo.getName() + ".xml");

            try {
                BufferedWriter bw = Files.newBufferedWriter(Paths.get(targetFile.toURI()), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
                Throwable var14 = null;

                try {
                    Template template = this.cfg.getTemplate(tempFile);
                    template.process(root, bw);
                    log.info("Generate mybatis base mapper file {}", targetFile.getAbsolutePath());
                } catch (Throwable var24) {
                    var14 = var24;
                    throw var24;
                } finally {
                    if (bw != null) {
                        if (var14 != null) {
                            try {
                                bw.close();
                            } catch (Throwable var23) {
                                var14.addSuppressed(var23);
                            }
                        } else {
                            bw.close();
                        }
                    }

                }
            } catch (TemplateException var26) {
                log.warn("{}", var26.getMessage(), var26);
            }

        }
    }
}
