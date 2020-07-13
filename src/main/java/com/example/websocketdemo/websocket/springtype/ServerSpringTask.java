package com.example.websocketdemo.websocket.springtype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * 向客户端定时推消息
 * 打开定时就放开下面的注解
 * @Author: liulg
 * @Date: 2020/7/11 16:37
 */
@Slf4j
@Component
//@EnableScheduling
public class ServerSpringTask {
    @Scheduled(cron = "0/8 * * * * ?")
    public void sendPlayEnd() throws Exception {
        log.info("【spring推送消息】开始执行");
        Set set = WebHandler.hashMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            WebSocketSession session = (WebSocketSession) WebHandler.hashMap.get(iterator.next());
            session.sendMessage(new TextMessage("admin:"+new Random().nextInt()));
        }
        log.info("【spring推送消息】执行结束");
    }
}
