package com.example.websocketdemo.websocket.stomptype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @Author: liulg
 * @Date: 2020/7/12 21:56
 */
@Slf4j
@Component
public class WebSocketEventListener {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    /**
     * 服务端监听连接建立完成
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        String sessionId=event.getMessage().getHeaders().get("simpSessionId")+"";
        log.info("Received a new web socket connection,sessionId:{}",sessionId);
    }
    //监听关闭连接
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        System.out.println(sessionId);
        Conver conver = new Conver();
        conver.setContent("有人关闭连接");
        messagingTemplate.convertAndSend("/topic/hello",conver);
    }
}
