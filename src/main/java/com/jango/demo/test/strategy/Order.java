package com.jango.demo.test.strategy;

import java.math.BigDecimal;

/**
 * @author xiongfeixiang
 * @title Order
 * @date 2020/9/28 15:24
 * @since v1.0.0
 */
public class Order {
    private String orderNo;//订单编号
    private BigDecimal amount;//订单金额
    private String source;//订单来源

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
