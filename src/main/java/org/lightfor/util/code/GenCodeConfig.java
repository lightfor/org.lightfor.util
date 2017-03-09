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
}
