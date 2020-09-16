package com.jango.demo.mapper;

import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmAreaMapper {
    int countByExample(SmAreaExample example);

    int deleteByExample(SmAreaExample example);

    int deleteByPrimaryKey(String areaId);

    int insert(SmArea record);

    int insertSelective(SmArea record);

    List<SmArea> selectByExample(SmAreaExample example);

    SmArea selectByPrimaryKey(String areaId);

    int updateByExampleSelective(@Param("record") SmArea record, @Param("example") SmAreaExample example);

    int updateByExample(@Param("record") SmArea record, @Param("example") SmAreaExample example);

    int updateByPrimaryKeySelective(SmArea record);

    int updateByPrimaryKey(SmArea record);
}