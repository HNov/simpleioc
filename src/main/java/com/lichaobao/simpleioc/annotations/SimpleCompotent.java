package com.lichaobao.simpleioc.annotations;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 * 标记需要生成的bean
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleCompotent {
    String value() default "";
}
