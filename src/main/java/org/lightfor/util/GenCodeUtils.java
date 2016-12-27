package org.lightfor.util;

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
    private static final int TYPE_UNKNOWN = 99;


    /**
     *
     * @param fields 成员变量
     * @param withAnnotation    是否生成注解
     * @return 生成代码
     */
    public static String genFields(String[] fields, Boolean withAnnotation) {

        String[] split;

        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher;
        String type;
        int typeResult;

        StringBuffer sb = new StringBuffer(200);
        for (String field : fields) {
            field = field.toLowerCase();
            if (field.contains("\t")) {
                split = field.split("\t");
                matcher = pattern.matcher(split[0]);
                type = split[1].toLowerCase();
                String column = split[0];
                if (split.length == 3) {
                    sb.append("/** ").append(split[2]).append(" */").append("\n");
                }

                if (type.contains("varchar")) {
                    typeResult = TYPE_VARCHAR;
                } else if (type.contains("number")) {
                    typeResult = TYPE_NUMBER;
                } else if (type.contains("timestamp")) {
                    typeResult = TYPE_TIMESTAMP;
                } else {
                    typeResult = TYPE_UNKNOWN;
                }

                if(withAnnotation){
                    if (column.compareToIgnoreCase("id") == 0) {
                        sb.append("@Id");
                    } else if (column.compareToIgnoreCase("version") == 0) {
                        typeResult = TYPE_VERSION;
                        sb.append("@Version\n@Column(name = \"VERSION\", nullable = false, precision = 22, scale = 0)");
                    } else {
                        sb.append("@Column(name=\"").append(split[0].toUpperCase()).append("\"");
                        if (typeResult == TYPE_VARCHAR) {
                            String length = RegexUtils.returnFirstMatch(type, "\\((\\d+)\\)");
                            if (length != null) {
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
                            sb.append(")\n").append("@Temporal(TemporalType.TIMESTAMP)");
                        } else {
                            sb.append(")");
                        }
                    }
                    sb.append("\n");
                }

                sb.append("private ");
                if (typeResult == TYPE_VARCHAR) {
                    sb.append("String ");
                } else if (typeResult == TYPE_NUMBER) {
                    sb.append("BigDecimal ");
                } else if (typeResult == TYPE_TIMESTAMP) {
                    sb.append("Date ");
                } else if (typeResult == TYPE_VERSION) {
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
}
