package com.jango.demo.controller;

import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import com.jango.demo.domain.BaseResp;
import com.jango.demo.mapper.SmAreaMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author xiongfeixiang
 * @title RestfulApiTestController
 * @date 2019/7/18 17:55
 * @since v1.0.0
 */
@RequestMapping("/jango/area")
@Controller
@Api(tags = "区域管理")
public class RestfulApiTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulApiTestController.class);

    @Resource
    SmAreaMapper smAreaMapper;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新建区域信息", notes = "根据SmArea对象创建区域信息")
    public BaseResp post(@ModelAttribute SmArea area) {
        int result = smAreaMapper.insertSelective(area);
        return new BaseResp(result);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除区域信息", notes = "根据SmArea对象删除")
    public String del(@ModelAttribute SmArea area) {
        SmAreaExample example = new SmAreaExample();
        example.createCriteria()
                .andAreaIdEqualTo(area.getAreaId())
                .andAreaNameEqualTo(area.getAreaName())
                .andUpperAreaIdEqualTo(area.getUpperAreaId());
        int result = smAreaMapper.deleteByExample(example);
        return "delete 【" + result + "】 records";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "根据区域id删除", notes = "根据id删除SmArea对象")
    public String delById(@PathVariable String id) {
        SmAreaExample example = new SmAreaExample();
        example.createCriteria()
                .andAreaIdEqualTo(id);
        int result = smAreaMapper.deleteByExample(example);
        return "delete 【" + result + "】 records";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据区域id获取详情", notes = "根据区域id获取详情")
    public BaseResp detail(@PathVariable String id) {
        return new BaseResp<>(smAreaMapper.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "根据区域id更新", notes = "根据区域id更新")
    public BaseResp detail(@PathVariable String id, SmArea area) {
        SmArea vo = new SmArea();
        vo.setAreaId(id);
        vo.setAreaName(area.getAreaName());
        vo.setUpperAreaId(area.getUpperAreaId());
        return new BaseResp<>(smAreaMapper.updateByPrimaryKey(vo));
    }
}
