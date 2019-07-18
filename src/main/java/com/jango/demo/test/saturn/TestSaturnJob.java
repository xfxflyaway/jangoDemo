package com.jango.demo.test.saturn;

import com.jango.demo.domain.dto.SmAreaExample;
import com.jango.demo.service.SaturnTestService;
import com.vip.saturn.job.AbstractSaturnJavaJob;
import com.vip.saturn.job.SaturnJobExecutionContext;
import com.vip.saturn.job.SaturnJobReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiongfeixiang
 * @title TestSaturnJob
 * @date 2019/2/1 下午3:24
 * @since v1.0.0
 */
@Component
public class TestSaturnJob extends AbstractSaturnJavaJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSaturnJob.class);
    @Resource
    SaturnTestService saturnTestService;

    //    SmAreaMapper areaMapper;

    @Override
    public SaturnJobReturn handleJavaJob(String jobName, Integer shardItem, String shardParam, SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
        // 返回一个SaturnJobReturn对象，默认返回码是200表示正常的返回
        SmAreaExample example = new SmAreaExample();
//        List<SmArea> list = areaMapper.selectByExample(example);
//        list.forEach(v -> LOGGER.info(v.getAreaName()));
        saturnTestService.doSth();
        System.out.println("hello w");
        return new SaturnJobReturn("我是分片" + shardItem + "的处理结果");
    }
}
