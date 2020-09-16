package com.jango.demo.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@ApiModel(description = "通用响应实体")
public class BaseResp<T> {
    @ApiModelProperty(value = "响应code")
    protected int code = 200;
    @ApiModelProperty(value = "响应message")
    protected String message = "success";
    @ApiModelProperty(value = "响应数据")
    protected T data;

    public BaseResp() {
    }

    public BaseResp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResp(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isValid() {
        if (null != this && Objects.equals(this.code, HttpStatus.OK.value()) && null != this.getData()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
