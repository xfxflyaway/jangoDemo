package com.jango.demo.mapper;

import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import java.util.List;

public interface SmAreaMapper {
    int countByExample(SmAreaExample example);

    int insert(SmArea record);

    int insertSelective(SmArea record);

    List<SmArea> selectByExample(SmAreaExample example);
}