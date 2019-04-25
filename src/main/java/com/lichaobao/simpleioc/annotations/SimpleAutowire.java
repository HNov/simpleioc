package com.lichaobao.simpleioc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 * 标记需要注入的属性
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SimpleAutowire {
    String value() default "";
}
