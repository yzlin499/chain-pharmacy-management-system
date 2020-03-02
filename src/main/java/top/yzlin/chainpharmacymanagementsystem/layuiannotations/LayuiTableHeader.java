package top.yzlin.chainpharmacymanagementsystem.layuiannotations;

import java.lang.annotation.*;

/**
 * @author yzlin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LayuiTableHeader {
    String TYPE_NORMAL = "normal";
    String TYPE_CHECKBOX = "checkbox";
    String TYPE_RADIO = "radio";
    String TYPE_NUMBERS = "numbers";
    String TYPE_SPACE = "space";

    String tableName() default "";

    String type() default "";

    boolean isCanDelete() default true;
}
