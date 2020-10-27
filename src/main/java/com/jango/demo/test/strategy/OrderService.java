package com.jango.demo.test.strategy;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiongfeixiang
 * @title OrderService
 * @date 2020/9/28 15:40
 * @since v1.0.0
 */
@Service
public class OrderService {

    private Map<String, OrderHandler> orderHandleMap;

    @Resource
    public void setHandler(List<OrderHandler> orderHandlers){
        // 注入各种类型的订单处理类
        orderHandleMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class).source(),
                        v -> v, (v1, v2) -> v1));
    }

    public void orderService(Order order) {
        // ...一些前置处理

        // 通过订单来源确定对应的handler
        OrderHandler orderHandler = orderHandleMap.get(order.getSource());
        orderHandler.handle(order);

        // ...一些后置处理
    }
}
