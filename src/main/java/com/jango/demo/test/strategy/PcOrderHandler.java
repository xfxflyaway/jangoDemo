package com.jango.demo.test.strategy;

import org.springframework.stereotype.Service;

/**
 * @author xiongfeixiang
 * @title PcOrderHandler
 * @date 2020/9/28 15:28
 * @since v1.0.0
 */
@Service
@OrderHandlerType(source = "PC")
public class PcOrderHandler implements OrderHandler {
    /**
     * 处理PC端订单
     *
     * @param order
     */
    @Override
    public void handle(Order order) {
        System.out.println("处理pc端订单");
    }
}
