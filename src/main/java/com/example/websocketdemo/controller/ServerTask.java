package com.example.websocketdemo.controller;

import cn.hutool.json.JSONUtil;
import com.example.websocketdemo.model.ChatMessage;
import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @Author: liulg
 * @Date: 2020/7/11 16:37
 */
@Component
@EnableScheduling
public class ServerTask {
    @Autowired
    private SimpMessagingTemplate wsTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ServerTask.class);


     // 按照标准时间来算，每隔 2s 执行一次


    @Scheduled(cron = "0/30 * * * * ?")
    public void websocket() throws Exception {
        logger.info("【推送消息】开始执行");
        // 查询服务器状态
        ChatMessage message = new ChatMessage();
        message.setSender("admin");
        message.setType(ChatMessage.MessageType.CHAT);
        message.setContent(LocalDateTime.now().toString());
        wsTemplate.convertAndSend("/topic/public",JSONUtil.parseObj(message));
        logger.info("【推送消息】执行结束");
    }
}
