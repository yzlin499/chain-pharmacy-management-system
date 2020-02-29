package top.yzlin.chainpharmacymanagementsystem.layuiannotations;

import lombok.ToString;

import java.lang.annotation.Annotation;

@ToString
public class LayuiTableFieldImpl implements LayuiTableField {
    private String field;

    public LayuiTableFieldImpl(String field) {
        this.field = field;
    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public String value() {
        return field;
    }

    @Override
    public String width() {
        return "";
    }

    @Override
    public int minWidth() {
        return 60;
    }

    @Override
    public boolean sort() {
        return true;
    }

    @Override
    public boolean totalRow() {
        return false;
    }

    @Override
    public String edit() {
        return "";
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return LayuiTableField.class;
    }
}
