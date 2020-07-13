package com.example.websocketdemo.websocket.jdktype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * 打开定时就放开下面的注解
 * @Author: liulg
 * @Date: 2020/7/11 16:37
 */
@Slf4j
@Component
//@EnableScheduling
public class ServerTask {
    @Scheduled(cron = "0/10 * * * * ?")
    public void websocket() throws Exception {
        log.info("【推送消息】开始执行");
        Set set = WebSocketServer.hashMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Session session = (Session) WebSocketServer.hashMap.get(iterator.next());
            session.getBasicRemote().sendText("admin:"+new Random().nextInt());
        }
        log.info("【推送消息】执行结束");
    }
}
