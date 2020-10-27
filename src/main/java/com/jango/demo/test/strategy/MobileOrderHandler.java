package com.jango.demo.test.strategy;

import org.springframework.stereotype.Service;

/**
 * @author xiongfeixiang
 * @title MobileOrderHandler
 * @date 2020/9/28 15:29
 * @since v1.0.0
 */
@Service
@OrderHandlerType(source = "MOBILE")
public class MobileOrderHandler implements OrderHandler {
    /**
     * 处理移动端订单
     *
     * @param order
     */
    @Override
    public void handle(Order order) {
        System.out.println("处理移动端订单");
    }
}
