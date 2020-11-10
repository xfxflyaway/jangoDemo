package com.jango.demo;

import com.alibaba.fastjson.JSON;
import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.utils.UUIDUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(post("/jango/getConfigXfxDto")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    public void testAddArea() throws Exception {
        SmArea area = new SmArea();
        area.setAreaId(UUIDUtil.genUUID());
        area.setUpperAreaId("9999");
        area.setAreaName("直布罗陀海峡");
        mockMvc.perform(
                post("/jango/area/add")
                        .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(area))
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

}

