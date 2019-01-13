package com.diao.qiang.task;

import com.diao.qiang.service.TxffcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
    @Autowired
    public TxffcService txffcService;

//    @Scheduled(initialDelay=2000, fixedDelay=30000)
    // cron表达式
    @Scheduled(cron = "10/40 * * * * ?")
    public void insert(){

        logger.info("this time :{}", sdf.format(new Date()));
        txffcService.insertTxffc();
    }
}
