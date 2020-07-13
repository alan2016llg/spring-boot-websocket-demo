package com.example.websocketdemo.websocket.springtype;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定义websocket事件处理类
 * @Author: liulg
 * @Date: 2020/7/12 18:56
 */
@Component
public class WebHandler extends TextWebSocketHandler {
    public static ConcurrentHashMap hashMap = new ConcurrentHashMap();

    /**
     * 连接关闭
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("token");
        System.out.println("连接关闭:"+token);
        if (token != null) {
            // 用户退出，移除缓存
            hashMap.remove(token.toString());
        }

    }

    /*
    连接成功
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getAttributes().get("token");
        if (token != null) {
            // 用户连接成功，放入在线用户缓存
            hashMap.put(token.toString(), session);
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
    }

    /**
     * 接收消息触发
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得客户端传来的消息
        String payload = message.getPayload();
        System.out.println("server 接收到 client 发送的 " + payload);
        session.sendMessage(new TextMessage("server 发送给 client 消息 " + payload + " " + LocalDateTime.now().toString()));
    }


}
