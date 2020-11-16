package com.jango.demo.controller;

import com.alibaba.fastjson.JSON;
import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import com.jango.demo.mapper.SmAreaMapper;
import com.jango.demo.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/jango/cache")
public class CacheTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheTestController.class);

    @Resource
    SmAreaMapper smAreaMapper;

    @Cacheable(value = "area",key = "#areaName")
    @RequestMapping("/getByAreaName")
    public List<SmArea> getByAreaName(String areaName) {
        LOGGER.info("getByAreaName area name :{}", areaName);
        SmAreaExample example = new SmAreaExample();
        example.createCriteria().andAreaNameEqualTo(areaName);
        List<SmArea> list = smAreaMapper.selectByExample(example);
        list.forEach(v -> System.out.println(v.getAreaName()));
        return list;
    }

    @RequestMapping("/addArea")
    public void addArea(SmArea area) {
        LOGGER.info("updCache area info :{}", JSON.toJSONString(area));
        if (null == area.getAreaId()) {
            area = new SmArea();
            area.setAreaId(UUIDUtil.genUUID());
            area.setAreaName("芝加哥");
            area.setUpperAreaId("lange");
        }
        smAreaMapper.insertSelective(area);
    }

    @CachePut(value = "area")
    @RequestMapping("/updArea")
    public void updArea(SmArea area) {
        LOGGER.info("updCache area info :{}", JSON.toJSONString(area));
        smAreaMapper.updateByPrimaryKey(area);
    }

    @CacheEvict(value = "area",key = "#areaName")
    @RequestMapping("/delArea")
    public void delArea(String areaName) {
        LOGGER.info("delArea area name :{}", areaName);
        SmAreaExample example = new SmAreaExample();
        example.createCriteria().andAreaNameEqualTo(areaName);
        smAreaMapper.deleteByExample(example);
    }


}
