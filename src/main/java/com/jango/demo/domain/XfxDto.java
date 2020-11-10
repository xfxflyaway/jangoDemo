package com.jango.demo.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiongfeixiang
 * @title XfxDto
 * @date 2020/11/10 17:36
 * @since v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "xfx")
public class XfxDto {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
