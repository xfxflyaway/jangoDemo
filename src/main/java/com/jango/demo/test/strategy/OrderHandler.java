package com.jango.demo.test.strategy;

/**
 * @author
 * @title OrderHandler
 * @date 2020/9/28 15:27
 * @since v1.0.0
 */
public interface OrderHandler {
    /**
     * 处理订单
     *
     * @param order
     */
    void handle(Order order);
}
