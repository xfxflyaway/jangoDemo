package com.jango.demo.utils;

import java.util.UUID;

/**
 * @author xiongfeixiang
 * @title UUIDUtil
 * @date 2020/11/10 17:59
 * @since v1.0.0
 */
public class UUIDUtil {
    /**
     * 生成uuid
     *
     * @return
     */
    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
