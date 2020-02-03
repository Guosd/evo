package com.github.framework.evo.code.generator;


import com.github.framework.evo.code.generator.schema.Column;
import com.github.framework.evo.code.generator.schema.Database;
import com.github.framework.evo.code.generator.schema.PrimaryKey;
import com.github.framework.evo.code.generator.schema.Table;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class DatabaseUtils {
    private static final Logger log = LoggerFactory.getLogger(DatabaseUtils.class);
    private Connection conn;
    private String catalog;
    private String schema;
    private Database database;

    private DatabaseUtils() {
    }

    public Database getDatabase() {
        return this.database;
    }

    public static DatabaseUtils getInstance(Connection conn, String catalog, String schema) {
        DatabaseUtils obj = new DatabaseUtils();

        try {
            DatabaseMetaData metaData = conn.getMetaData();
            Database database = new Database();
            database.setProductName(metaData.getDatabaseProductName());
            database.setProductVersion(metaData.getDatabaseProductVersion());
            obj.conn = conn;
            obj.database = database;
            obj.catalog = catalog;
            obj.schema = schema;
        } catch (SQLException var6) {
            log.warn("{}", var6);
        }

        return obj;
    }

    public Map<String, String> getAllTableNamesMap() {
        ResultSet rs = null;
        HashMap result = new HashMap();

        try {
            DatabaseMetaData metaData = this.conn.getMetaData();
            rs = metaData.getTables(this.catalog, this.schema, (String)null, new String[]{"TABLE", "VIEW"});

            while(rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                result.put(tableName.toLowerCase(Locale.getDefault()), tableName);
            }

            rs.close();
        } catch (SQLException var5) {
            log.warn("{}", var5);
        }

        return result;
    }

    public Table getTableInfo(String tableName) {
        Table table = new Table();
        table.setName(tableName);
        ResultSet rs = null;

        try {
            DatabaseMetaData metaData = this.conn.getMetaData();
            rs = metaData.getTables(this.catalog, this.schema, tableName, new String[]{"TABLE", "VIEW"});
            if (rs.next()) {
                table.setComment(rs.getString("REMARKS"));
            }

            rs.close();
        } catch (SQLException var5) {
            log.warn("{}", var5);
        }

        if (table.getComment() == null) {
            table.setComment("");
        }

        table.setColumns(this.getTableColumns(tableName));
        table.setPrimaryKeys(this.getTablePrimaryKeys(tableName));
        return table;
    }

    private List<Column> getTableColumns(String tableName) {
        List<Column> columns = new ArrayList();
        Map<String, String> map = new HashMap();
        ResultSet rs = null;

        try {
            DatabaseMetaData metaData = this.conn.getMetaData();
            rs = metaData.getColumns(this.catalog, this.schema, tableName, "%");

            while(rs.next()) {
                Column column = new Column();
                column.setName(rs.getString("COLUMN_NAME"));
                column.setType(rs.getString("TYPE_NAME"));
                column.setSize(rs.getInt("COLUMN_SIZE"));
                column.setComment(rs.getString("REMARKS"));
                column.setNullable(rs.getBoolean("NULLABLE"));
                column.setDefaultValue(rs.getString("COLUMN_DEF"));
                if (column.getType().toLowerCase(Locale.US).contains("serial")) {
                    column.setAutoIncrement(true);
                } else {
                    column.setAutoIncrement(this.isAutoIncrement(rs));
                }

                if (column.getComment() == null) {
                    column.setComment("");
                }

                if (map.containsKey(column.getName())) {
                    throw new IllegalArgumentException("发现重复字段，必须设置dbcatalog参数");
                }

                map.put(column.getName(), "");
                columns.add(column);
            }

            rs.close();
        } catch (SQLException var7) {
            log.warn("{}", var7);
        }

        return columns;
    }

    private boolean isAutoIncrement(ResultSet rs) {
        boolean result = false;

        try {
            String mysqlResult = rs.getString("IS_AUTOINCREMENT");
            if ("YES".equals(mysqlResult)) {
                result = true;
            } else if ("NO".equals(mysqlResult)) {
                result = false;
            } else {
                result = rs.getBoolean("IS_AUTOINCREMENT");
            }
        } catch (NumberFormatException | SQLException var4) {
            if (!var4.getMessage().contains("No such column name") && !var4.getMessage().contains("列名无效")) {
                log.warn("{}", var4.getMessage(), var4);
            }

            result = false;
        }

        return result;
    }

    private List<PrimaryKey> getTablePrimaryKeys(String tableName) {
        List<PrimaryKey> primaryKeys = new ArrayList();
        ResultSet rs = null;

        try {
            DatabaseMetaData metaData = this.conn.getMetaData();
            rs = metaData.getPrimaryKeys(this.catalog, this.schema, tableName);

            while(rs.next()) {
                PrimaryKey obj = new PrimaryKey();
                obj.setColumnName(rs.getString("COLUMN_NAME"));
                obj.setKeySeq(rs.getInt("KEY_SEQ"));
                obj.setPkName(rs.getString("PK_NAME"));
                primaryKeys.add(obj);
            }

            rs.close();
        } catch (SQLException var6) {
            log.warn("{}", var6);
        }

        return primaryKeys;
    }
}
