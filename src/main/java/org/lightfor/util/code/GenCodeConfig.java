package org.lightfor.util.code;

/**
 * config about how to generate code
 * Created by Light on 2017/3/9.
 */
public class GenCodeConfig {
    private String[] fields;
    private boolean withAnnotation = true;
    private boolean withCharLength = false;
    private boolean withTemporalAnnotation = false;

    private String tableName;
    private String tableComment;

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public boolean isWithAnnotation() {
        return withAnnotation;
    }

    public void setWithAnnotation(boolean withAnnotation) {
        this.withAnnotation = withAnnotation;
    }

    public boolean isWithCharLength() {
        return withCharLength;
    }

    public void setWithCharLength(boolean withCharLength) {
        this.withCharLength = withCharLength;
    }

    public boolean isWithTemporalAnnotation() {
        return withTemporalAnnotation;
    }

    public void setWithTemporalAnnotation(boolean withTemporalAnnotation) {
        this.withTemporalAnnotation = withTemporalAnnotation;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
