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

    private boolean indentWithFourSpaces = false;

    private String tableName;
    private String tableComment;

    private String moduleName;

    private boolean updateMethod = true;

    private boolean saveMethod = true;

    private boolean deleteMethod = true;

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

    public boolean isIndentWithFourSpaces() {
        return indentWithFourSpaces;
    }

    public void setIndentWithFourSpaces(boolean indentWithFourSpaces) {
        this.indentWithFourSpaces = indentWithFourSpaces;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod(boolean updateMethod) {
        this.updateMethod = updateMethod;
    }

    public boolean isSaveMethod() {
        return saveMethod;
    }

    public void setSaveMethod(boolean saveMethod) {
        this.saveMethod = saveMethod;
    }

    public boolean isDeleteMethod() {
        return deleteMethod;
    }

    public void setDeleteMethod(boolean deleteMethod) {
        this.deleteMethod = deleteMethod;
    }
}
