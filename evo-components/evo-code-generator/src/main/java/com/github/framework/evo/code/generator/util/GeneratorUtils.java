package com.github.framework.evo.code.generator.util;


import com.github.framework.evo.code.generator.schema.Column;
import com.github.framework.evo.code.generator.schema.PrimaryKey;
import com.github.framework.evo.code.generator.schema.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class GeneratorUtils {
    private GeneratorUtils() {
    }

    public static List<String> getTableColumnTypes(Table table) {
        List<String> types = new ArrayList();
        List<Column> columns = table.getColumns();
        int size = columns.size();

        for(int i = 0; i < size; ++i) {
            Column column = (Column)columns.get(i);
            types.add(column.getType());
        }

        return types;
    }

    public static List<String> getTablePrimaryKeyTypes(Table table) {
        List<String> types = new ArrayList();
        List<Column> columns = table.getColumns();
        Map<String, String> columnTypeMap = new HashMap();
        int size = columns.size();

        for(int i = 0; i < size; ++i) {
            Column column = (Column)columns.get(i);
            columnTypeMap.put(column.getName(), column.getType());
        }

        List<PrimaryKey> primaryKeys = table.getPrimaryKeys();
        size = primaryKeys.size();

        for(int i = 0; i < size; ++i) {
            PrimaryKey primaryKey = (PrimaryKey)primaryKeys.get(i);
            types.add(columnTypeMap.get(primaryKey.getColumnName()));
        }

        return types;
    }

    public static boolean hasColumn(Table table, String columnName) {
        boolean result = false;
        List<Column> columns = table.getColumns();
        int size = columns.size();

        for(int i = 0; i < size; ++i) {
            Column column = (Column)columns.get(i);
            if (getObjectName(column.getName()).equalsIgnoreCase(columnName)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static List<Column> getTablePrimaryKeysColumns(Table table) {
        Map<String, Column> columnMap = new HashMap();
        List<Column> columns = table.getColumns();
        int size = columns.size();

        for(int i = 0; i < size; ++i) {
            Column column = (Column)columns.get(i);
            columnMap.put(column.getName().toLowerCase(Locale.getDefault()), column);
        }

        List<Column> result = new ArrayList();
        List<PrimaryKey> primaryKeys = table.getPrimaryKeys();
        size = primaryKeys.size();

        for(int i = 0; i < size; ++i) {
            PrimaryKey primaryKey = (PrimaryKey)primaryKeys.get(i);
            result.add(columnMap.get(primaryKey.getColumnName().toLowerCase(Locale.getDefault())));
        }

        return result;
    }

    public static String getKeyProperty(final Table table) {
        List<Column> primaryKeysColumns = getTablePrimaryKeysColumns(table);
        String result = "";
        if (primaryKeysColumns.size() == 1) {
            Column column = (Column)primaryKeysColumns.get(0);
            if (column.isAutoIncrement()) {
                result = column.getName();
            }
        }

        return result;
    }

    public static boolean isAutoIncrementPK(final Table table) {
        List<Column> primaryKeysColumns = getTablePrimaryKeysColumns(table);
        if (primaryKeysColumns.size() == 1) {
            Column column = (Column)primaryKeysColumns.get(0);
            if (column.isAutoIncrement()) {
                return true;
            }
        }

        return false;
    }

    public static List<String> getTablePrimaryKeysCloumnNames(Table table) {
        List<String> result = new ArrayList();
        List<PrimaryKey> primaryKeys = table.getPrimaryKeys();
        int size = primaryKeys.size();

        for(int i = 0; i < size; ++i) {
            PrimaryKey primaryKey = (PrimaryKey)primaryKeys.get(i);
            result.add(primaryKey.getColumnName().toLowerCase(Locale.getDefault()));
        }

        return result;
    }

    public static List<String> getTablePrimaryKeysTypes(Table table) {
        Map<String, String> columnMap = new HashMap();
        List<Column> columns = table.getColumns();
        int size = columns.size();

        for(int i = 0; i < size; ++i) {
            Column column = (Column)columns.get(i);
            columnMap.put(column.getName().toLowerCase(Locale.getDefault()), column.getType());
        }

        List<String> types = new ArrayList();
        List<PrimaryKey> primaryKeys = table.getPrimaryKeys();
        size = primaryKeys.size();

        for(int i = 0; i < size; ++i) {
            PrimaryKey primaryKey = (PrimaryKey)primaryKeys.get(i);
            types.add(columnMap.get(primaryKey.getColumnName().toLowerCase(Locale.getDefault())));
        }

        return types;
    }

    public static boolean containsDate(List<String> types) {
        Iterator var1 = types.iterator();

        String t;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            String type = (String)var1.next();
            t = type.toLowerCase(Locale.getDefault());
        } while(!t.contains("date") && !t.contains("time"));

        return true;
    }

    public static boolean containsBigDecimal(List<String> types) {
        Iterator var1 = types.iterator();

        String type;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            type = (String)var1.next();
        } while(!type.toLowerCase(Locale.getDefault()).contains("number") && !type.toLowerCase(Locale.getDefault()).contains("numeric") && !type.toLowerCase(Locale.getDefault()).contains("decimal"));

        return true;
    }

    public static String getInstanceName(String field) {
        return WordFileUtils.getBeautyInstanceName(field);
    }

    public static String getObjectName(String field) {
        return WordFileUtils.getBeautyObjectName(field);
    }
}
