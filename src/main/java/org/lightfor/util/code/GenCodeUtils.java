package org.lightfor.util.code;

import org.lightfor.util.RegexUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代码生成Utils
 * Created by Light on 2016/8/9.
 */
public enum  GenCodeUtils {
    INSTANCE;

    private static final int TYPE_VARCHAR = 1;
    private static final int TYPE_NUMBER = 2;
    private static final int TYPE_TIMESTAMP = 3;
    private static final int TYPE_VERSION = 4;
    private static final int TYPE_ID = 5;
    private static final int TYPE_UNKNOWN = 99;


    /**
     *
     * @param genCodeConfig 代码生成配置
     * @return 生成代码
     */
    public static String genFieldsStr(GenCodeConfig genCodeConfig) {

        String[] split;

        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher;
        String type;
        String column;
        int typeResult;

        StringBuffer sb = new StringBuffer(200);
        for (String field : genCodeConfig.getFields()) {
            field = field.toLowerCase();
            if (field.contains("\t")) {
                split = field.split("\t");
                matcher = pattern.matcher(split[0]);
                type = split[1].toLowerCase();
                column = split[0];
                if (split.length == 3) {
                    if(genCodeConfig.isIndentWithFourSpaces()){
                        sb.append("    ");
                    }
                    sb.append("/** ").append(split[2]).append(" */").append("\n");
                }

                if(column.compareToIgnoreCase("id") == 0){
                    typeResult = TYPE_ID;
                } else if(column.compareToIgnoreCase("version") == 0){
                    typeResult = TYPE_VERSION;
                } else if (type.contains("varchar")) {
                    typeResult = TYPE_VARCHAR;
                } else if (type.contains("number")) {
                    typeResult = TYPE_NUMBER;
                } else if (type.contains("timestamp")) {
                    typeResult = TYPE_TIMESTAMP;
                } else {
                    typeResult = TYPE_UNKNOWN;
                }

                if(genCodeConfig.isWithAnnotation()){
                    if (typeResult == TYPE_ID) {
                        sb.append("@Id\n");
                        sb.append("@SequenceGenerator( name = \"").append(genCodeConfig.getTableName()).append("_SEQ_GENERATOR\", sequenceName = \"").append(genCodeConfig.getTableName()).append("_SEQ\", allocationSize = 1 )\n");
                        sb.append("@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = \"").append(genCodeConfig.getTableName()).append("_SEQ_GENERATOR\" )\n");
                        sb.append("@Column( name = \"ID\" )");
                    } else if (typeResult == TYPE_VERSION) {
                        sb.append("@Version\n@Column(name = \"VERSION\", nullable = false, precision = 22)");
                    } else {
                        sb.append("@Column(name=\"").append(split[0].toUpperCase()).append("\"");
                        if (typeResult == TYPE_VARCHAR) {
                            String length = RegexUtils.returnFirstMatch(type, "\\((\\d+)\\)");
                            if (genCodeConfig.isWithCharLength() && length != null) {
                                sb.append(", length=").append(length);
                            }
                            sb.append(")");
                        } else if (typeResult == TYPE_NUMBER) {
                            String precision = RegexUtils.returnFirstMatch(type, "number\\((\\d+)");
                            if (precision != null) {
                                sb.append(", precision=").append(precision);
                            }
                            String scale = RegexUtils.returnFirstMatch(type, ",(\\d+)\\)");
                            if (scale != null) {
                                sb.append(", scale=").append(scale);
                            }
                            sb.append(")");
                        } else if (typeResult == TYPE_TIMESTAMP) {
                            sb.append(")");
                            if(genCodeConfig.isWithTemporalAnnotation()){
                                sb.append("\n@Temporal(TemporalType.TIMESTAMP)");
                            }
                        } else {
                            sb.append(")");
                        }
                    }
                    sb.append("\n");
                }

                if(genCodeConfig.isIndentWithFourSpaces()){
                    sb.append("    ");
                }

                sb.append("private ");
                if (typeResult == TYPE_VARCHAR) {
                    sb.append("String ");
                } else if (typeResult == TYPE_NUMBER) {
                    sb.append("BigDecimal ");
                } else if (typeResult == TYPE_TIMESTAMP) {
                    sb.append("Date ");
                } else if (typeResult == TYPE_VERSION) {
                    sb.append("long ");
                } else if (typeResult == TYPE_ID) {
                    sb.append("Integer ");
                } else {
                    sb.append("String ");
                }
            } else {
                matcher = pattern.matcher(field);
                sb.append("String ");
            }
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);
            sb.append(";\n");

        }
        return sb.toString();
    }

    public static String genClassName(GenCodeConfig genCodeConfig){
        StringBuffer sb = new StringBuffer(32);
        Pattern pattern = Pattern.compile("^(\\w)|_(\\w)");
        Matcher matcher = pattern.matcher(genCodeConfig.getTableName().toLowerCase());
        while (matcher.find()) {
            if(matcher.group(1) != null){
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            } else if(matcher.group(2) != null){
                matcher.appendReplacement(sb, matcher.group(2).toUpperCase());
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String genCreateSQL(GenCodeConfig genCodeConfig){
        String[] split;

        String type;
        String column;
        String comment;

        StringBuilder createSQL = new StringBuilder(400);
        StringBuilder commentSQL = new StringBuilder(400);
        StringBuilder seqSQL = new StringBuilder(200);

        commentSQL.append("-- Add comments to the table\n");
        commentSQL.append("comment on table ").append(genCodeConfig.getTableName()).append("\n");
        commentSQL.append("  is '").append(genCodeConfig.getTableComment()).append("';\n");
        commentSQL.append("-- Add comments to the columns\n");

        seqSQL.append("CREATE SEQUENCE ").append(genCodeConfig.getTableName()).append("_SEQ").append(" MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20;");

        createSQL.append("-- Create table\n");
        createSQL.append("create table ").append(genCodeConfig.getTableName()).append("\n");
        createSQL.append("(\n");
        for (String field : genCodeConfig.getFields()) {
            field = field.toUpperCase();
            if (field.contains("\t")) {
                split = field.split("\t");
                column = split[0];
                type = split[1];
                comment = split[2];

                commentSQL.append("comment on column ").append(genCodeConfig.getTableName()).append(".").append(column).append("\n");
                commentSQL.append("  is '").append(comment).append("';\n");

                createSQL.append("  ").append(column).append("  ").append(type);

                if ("ID".equals(column)) {
                    createSQL.append(" PRIMARY KEY");
                }

                createSQL.append(",\n");
            }
        }
        createSQL.deleteCharAt(createSQL.length() - 1).deleteCharAt(createSQL.length() - 1).append("\n);");
        createSQL.append("\n").append(commentSQL).append("\n").append(seqSQL);
        return createSQL.toString();
    }


    public static List<Field> getFieldsForView(GenCodeConfig genCodeConfig){
        return getFields(genCodeConfig.getFieldForViewStr());
    }

    public static List<Field> getFieldsForAll(GenCodeConfig genCodeConfig){
        return getFields(genCodeConfig.getFieldsForAllStr());
    }

    private static List<Field> getFields(String fieldStr) {
        String[] split;

        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher;
        String type;
        String column;

        List<Field> fields = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (String fieldStrSplit : fieldStr.split("\n")) {
            sb.delete(0, sb.length());
            fieldStrSplit = fieldStrSplit.toLowerCase();
            split = fieldStrSplit.split("\t");
            matcher = pattern.matcher(split[0]);
            type = split[1].toLowerCase();
            column = split[0];
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);



            Field field = new Field();
            field.setColumn(split[0]);
            field.setComment(split[2]);
            field.setField(sb.toString());
            if(column.compareToIgnoreCase("id") == 0){
                field.setType("Integer");
            } else if(column.compareToIgnoreCase("version") == 0){
                field.setType("Integer");
            } else if (type.contains("varchar")) {
                field.setType("String");
            } else if (type.contains("number")) {
                field.setType("String");
            } else if (type.contains("timestamp")) {
                field.setType("Date");
            } else {
                field.setType("String");
            }
            fields.add(field);
        }
        return fields;
    }

    public static String getTableName(GenCodeConfig genCodeConfig){
        return genCodeConfig.getTableName();
    }
}
