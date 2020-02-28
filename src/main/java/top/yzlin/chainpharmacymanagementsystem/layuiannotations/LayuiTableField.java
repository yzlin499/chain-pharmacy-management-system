package top.yzlin.chainpharmacymanagementsystem.layuiannotations;

import java.lang.annotation.*;

/**
 * @author yzlin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LayuiTableField {
    String EDIT_TYPE_TEXT = "text";

    //value即title
    String value() default "";

    String field() default "";

    boolean enable() default true;

    int width() default 0;

    int minWidth() default 60;

    boolean sort() default true;

    boolean totalRow() default false;

    String edit() default "";
}
