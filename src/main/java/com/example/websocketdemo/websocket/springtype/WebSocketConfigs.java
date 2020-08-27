package com.example.websocketdemo.websocket.springtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Author: liulg
 * @Date: 2020/7/12 19:22
 */
@Configuration
@EnableWebSocket
public class WebSocketConfigs implements WebSocketConfigurer {
    @Autowired
    WebHandler handler;
    @Autowired
    MyInterceptor interceptor;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(handler, "/webspring")
                .addInterceptors(interceptor)
                .setAllowedOrigins("*");

    }
}
