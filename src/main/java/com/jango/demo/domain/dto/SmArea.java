package com.jango.demo.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "区域实体")
public class SmArea {
    @ApiModelProperty(value = "区域id")
    private String areaId;
    @ApiModelProperty(value = "区域名")
    private String areaName;
    @ApiModelProperty(value = "上级区域id")
    private String upperAreaId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUpperAreaId() {
        return upperAreaId;
    }

    public void setUpperAreaId(String upperAreaId) {
        this.upperAreaId = upperAreaId;
    }
}