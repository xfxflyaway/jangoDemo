package com.jango.demo.test.strategy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 订单处理类型
 *
 * @author
 * @title OrderHandlerType
 * @date 2020/9/28 15:31
 * @since v1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderHandlerType {
    String source() default "PC";
}
