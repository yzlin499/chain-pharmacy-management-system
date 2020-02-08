package top.yzlin.chainpharmacymanagementsystem.layuiannotations;

import java.lang.annotation.Annotation;

public class LayuiFormFieldImpl implements LayuiFormField {
    private LayuiTableField ltf;

    public LayuiFormFieldImpl(LayuiTableField ltf) {
        this.ltf = ltf;
    }


    @Override
    public String type() {
        return "";
    }

    @Override
    public String value() {
        return ltf.value();
    }

    @Override
    public String name() {
        return ltf.field();
    }

    @Override
    public String placeholder() {
        return ltf.value();
    }

    @Override
    public String[] verify() {
        return new String[0];
    }

    @Override
    public boolean enable() {
        return ltf.enable();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return LayuiFormField.class;
    }
}
