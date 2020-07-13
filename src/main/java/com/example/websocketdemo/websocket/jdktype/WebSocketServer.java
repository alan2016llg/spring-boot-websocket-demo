package com.example.websocketdemo.websocket.jdktype;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liulg
 * @Date: 2020/7/11 17:14
 */
@ServerEndpoint("/web")
@Component
public class WebSocketServer {

    public  static  ConcurrentHashMap hashMap = new ConcurrentHashMap();
    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("用户id:"+session.getId());
        hashMap.put(session.getId(),session);
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        return "servet 发送：" + text;
    }

    /**
     * 连接异常执行
     * @param session
     * @param t
     */
    @OnError
    public void onErr(Session session, Throwable t){
        System.out.println("连接异常");
    }

}
