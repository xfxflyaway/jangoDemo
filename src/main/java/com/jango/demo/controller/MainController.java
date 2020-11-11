package com.jango.demo.controller;

import com.alibaba.fastjson.JSON;
import com.jango.demo.domain.BaseResp;
import com.jango.demo.domain.XfxDto;
import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import com.jango.demo.mapper.SmAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xiongfeixiang
 * @title MainController
 * @date 2019/1/31 下午3:30
 * @since v1.0.0
 */
@Controller
@RequestMapping("/jango")
public class MainController {
    // swagger2
    // logback
    // jwt
    // guava eventbus cache

    @Resource
    XfxDto xfx;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Resource
    SmAreaMapper smAreaMapper;

    @RequestMapping("/index")
    public BaseResp index() {
        LOGGER.info("获取区域信息测试:{}", "boot");
        SmAreaExample example = new SmAreaExample();
        example.createCriteria().andAreaNameEqualTo("普陀区");
        List<SmArea> list = smAreaMapper.selectByExample(example);
        list.forEach(v -> System.out.println(v.getAreaName()));
        System.out.println(JSON.toJSONString(xfx));
        return new BaseResp(list);
    }

    @RequestMapping("/area/add")
    @Transactional
    public String add(@RequestBody SmArea area) {
        LOGGER.info("获取区域信息测试area:{}", JSON.toJSONString(area));
        int result = smAreaMapper.insertSelective(area);
        return "j add..." + result;
    }

    @RequestMapping(value = "/views/jsp")
    public ModelAndView jsp() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", xfx.getName());
        return mv;
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:\\data\\temp\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }

    @PostMapping("/upload_multi")
    @ResponseBody
    public String upload_multi(HttpServletRequest req) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file");
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = "D:\\data\\temp\\";
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                LOGGER.info("上传成功");
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
            }
        }

        return "上传成功！";
    }


}
