package com.yang.test.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: Lyon
 * @Date: 2020/9/21 16:19
 * @Description:
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);
//    @Scheduled(fixedRate=5000)
//    public  void  scheduleTask(){
//        logger.info("执行静态定时任务时间: " + LocalDateTime.now());
//
//    }

}
