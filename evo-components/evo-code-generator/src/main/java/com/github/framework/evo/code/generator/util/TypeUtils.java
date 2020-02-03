package com.github.framework.evo.code.generator.util;


import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TypeUtils {
    private static final Logger log = LoggerFactory.getLogger(TypeUtils.class);

    private TypeUtils() {
    }

    public static String processType(String type) {
        String result = "";
        if (type.endsWith(" identity")) {
            type = type.substring(0, type.length() - " identity".length());
        }

        String var2 = type.toLowerCase(Locale.getDefault());
        byte var3 = -1;
        switch(var2.hashCode()) {
            case -2135304102:
                if (var2.equals("mediumint")) {
                    var3 = 37;
                }
                break;
            case -2073465431:
                if (var2.equals("longtext")) {
                    var3 = 12;
                }
                break;
            case -2029845859:
                if (var2.equals("tinyblob")) {
                    var3 = 65;
                }
                break;
            case -2029316051:
                if (var2.equals("tinytext")) {
                    var3 = 10;
                }
                break;
            case -2000413939:
                if (var2.equals("numeric")) {
                    var3 = 58;
                }
                break;
            case -1770128238:
                if (var2.equals("mediumblob")) {
                    var3 = 66;
                }
                break;
            case -1769598430:
                if (var2.equals("mediumtext")) {
                    var3 = 11;
                }
                break;
            case -1700129346:
                if (var2.equals("_varchar")) {
                    var3 = 4;
                }
                break;
            case -1582180070:
                if (var2.equals("logngblob")) {
                    var3 = 67;
                }
                break;
            case -1480973478:
                if (var2.equals("_bytea")) {
                    var3 = 69;
                }
                break;
            case -1389167889:
                if (var2.equals("bigint")) {
                    var3 = 39;
                }
                break;
            case -1388966911:
                if (var2.equals("binary")) {
                    var3 = 61;
                }
                break;
            case -1382823772:
                if (var2.equals("bpchar")) {
                    var3 = 6;
                }
                break;
            case -1327778097:
                if (var2.equals("nvarchar")) {
                    var3 = 0;
                }
                break;
            case -1325958191:
                if (var2.equals("double")) {
                    var3 = 24;
                }
                break;
            case -1312398097:
                if (var2.equals("tinyint")) {
                    var3 = 35;
                }
                break;
            case -1271649960:
                if (var2.equals("float4")) {
                    var3 = 26;
                }
                break;
            case -1271649956:
                if (var2.equals("float8")) {
                    var3 = 25;
                }
                break;
            case -1254919979:
                if (var2.equals("varchar2")) {
                    var3 = 2;
                }
                break;
            case -1191034305:
                if (var2.equals("abstime")) {
                    var3 = 50;
                }
                break;
            case -1034364087:
                if (var2.equals("number")) {
                    var3 = 57;
                }
                break;
            case -990442113:
                if (var2.equals("pg_lsn")) {
                    var3 = 22;
                }
                break;
            case -905839116:
                if (var2.equals("serial")) {
                    var3 = 34;
                }
                break;
            case -873668077:
                if (var2.equals("timetz")) {
                    var3 = 45;
                }
                break;
            case -823652218:
                if (var2.equals("varbit")) {
                    var3 = 54;
                }
                break;
            case -606531192:
                if (var2.equals("smallint")) {
                    var3 = 36;
                }
                break;
            case -588555902:
                if (var2.equals("smalldatetime")) {
                    var3 = 47;
                }
                break;
            case -275146264:
                if (var2.equals("varbinary")) {
                    var3 = 62;
                }
                break;
            case -191744392:
                if (var2.equals("macaddr8")) {
                    var3 = 18;
                }
                break;
            case 3355:
                if (var2.equals("id")) {
                    var3 = 29;
                }
                break;
            case 97549:
                if (var2.equals("bit")) {
                    var3 = 53;
                }
                break;
            case 104431:
                if (var2.equals("int")) {
                    var3 = 30;
                }
                break;
            case 112680:
                if (var2.equals("raw")) {
                    var3 = 63;
                }
                break;
            case 113762:
                if (var2.equals("set")) {
                    var3 = 14;
                }
                break;
            case 114831:
                if (var2.equals("tid")) {
                    var3 = 9;
                }
                break;
            case 118807:
                if (var2.equals("xml")) {
                    var3 = 19;
                }
                break;
            case 3026845:
                if (var2.equals("blob")) {
                    var3 = 64;
                }
                break;
            case 3029738:
                if (var2.equals("bool")) {
                    var3 = 56;
                }
                break;
            case 3052374:
                if (var2.equals("char")) {
                    var3 = 5;
                }
                break;
            case 3053428:
                if (var2.equals("cidr")) {
                    var3 = 15;
                }
                break;
            case 3056636:
                if (var2.equals("clob")) {
                    var3 = 23;
                }
                break;
            case 3076014:
                if (var2.equals("date")) {
                    var3 = 43;
                }
                break;
            case 3118337:
                if (var2.equals("enum")) {
                    var3 = 13;
                }
                break;
            case 3237012:
                if (var2.equals("inet")) {
                    var3 = 16;
                }
                break;
            case 3237411:
                if (var2.equals("int2")) {
                    var3 = 31;
                }
                break;
            case 3237413:
                if (var2.equals("int4")) {
                    var3 = 32;
                }
                break;
            case 3237417:
                if (var2.equals("int8")) {
                    var3 = 42;
                }
                break;
            case 3271912:
                if (var2.equals("json")) {
                    var3 = 20;
                }
                break;
            case 3327612:
                if (var2.equals("long")) {
                    var3 = 38;
                }
                break;
            case 3496350:
                if (var2.equals("real")) {
                    var3 = 28;
                }
                break;
            case 3556653:
                if (var2.equals("text")) {
                    var3 = 8;
                }
                break;
            case 3560141:
                if (var2.equals("time")) {
                    var3 = 44;
                }
                break;
            case 3601339:
                if (var2.equals("uuid")) {
                    var3 = 70;
                }
                break;
            case 3704893:
                if (var2.equals("year")) {
                    var3 = 48;
                }
                break;
            case 55126294:
                if (var2.equals("timestamp")) {
                    var3 = 49;
                }
                break;
            case 64711720:
                if (var2.equals("boolean")) {
                    var3 = 55;
                }
                break;
            case 94224473:
                if (var2.equals("bytea")) {
                    var3 = 68;
                }
                break;
            case 97526364:
                if (var2.equals("float")) {
                    var3 = 27;
                }
                break;
            case 101429370:
                if (var2.equals("jsonb")) {
                    var3 = 21;
                }
                break;
            case 104079552:
                if (var2.equals("money")) {
                    var3 = 59;
                }
                break;
            case 236613373:
                if (var2.equals("varchar")) {
                    var3 = 3;
                }
                break;
            case 570418373:
                if (var2.equals("interval")) {
                    var3 = 51;
                }
                break;
            case 825098688:
                if (var2.equals("macaddr")) {
                    var3 = 17;
                }
                break;
            case 1091049190:
                if (var2.equals("reltime")) {
                    var3 = 52;
                }
                break;
            case 1325647299:
                if (var2.equals("_bpchar")) {
                    var3 = 7;
                }
                break;
            case 1538337030:
                if (var2.equals("bigint unsigned")) {
                    var3 = 40;
                }
                break;
            case 1542263633:
                if (var2.equals("decimal")) {
                    var3 = 60;
                }
                break;
            case 1788552003:
                if (var2.equals("nvarchar2")) {
                    var3 = 1;
                }
                break;
            case 1793702779:
                if (var2.equals("datetime")) {
                    var3 = 46;
                }
                break;
            case 1882307316:
                if (var2.equals("bigserial")) {
                    var3 = 41;
                }
                break;
            case 1958052158:
                if (var2.equals("integer")) {
                    var3 = 33;
                }
        }

        switch(var3) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                result = "String";
                break;
            case 24:
            case 25:
            case 26:
                result = "Double";
                break;
            case 27:
            case 28:
                result = "Float";
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
                result = "Integer";
                break;
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
                result = "Long";
                break;
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                result = "Date";
                break;
            case 53:
            case 54:
            case 55:
            case 56:
                result = "Boolean";
                break;
            case 57:
            case 58:
            case 59:
            case 60:
                result = "BigDecimal";
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
                result = "byte[]";
                break;
            case 70:
                result = "UUID";
                break;
            default:
                log.warn("Unsupport type [{}]", type);
        }

        return result;
    }
}
