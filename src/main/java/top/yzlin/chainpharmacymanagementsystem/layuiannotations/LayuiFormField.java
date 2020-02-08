package top.yzlin.chainpharmacymanagementsystem.layuiannotations;

import java.lang.annotation.*;

/**
 * @author yzlin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LayuiFormField {
    String TYPE_TEXT = "text";
    String TYPE_NUMBER = "number";
    String TYPE_TEXTAREA = "textarea";
    String TYPE_PASSWORD = "password";

    String VERIFY_REQUIRED = "required";

    String type() default "";

    //即title
    String value() default "";

    String name() default "";

    String placeholder() default "";

    String[] verify() default {};

    boolean enable() default true;
}
