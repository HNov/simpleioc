package com.lichaobao.simpleioc.annotations;

import java.lang.annotation.*;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 * 标记需要生成的单例bean
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SimpleBean {
    String name() default "";
}
