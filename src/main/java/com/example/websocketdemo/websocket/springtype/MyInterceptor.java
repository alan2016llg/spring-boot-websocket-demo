package com.example.websocketdemo.websocket.springtype;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 定义握手拦截器，认证相关可在这里做
 *
 * @Author: liulg
 * @Date: 2020/7/12 19:14
 */
@Component
public class MyInterceptor implements HandshakeInterceptor {

    /*
    握手前
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("握手开始");
        // 获得请求参数
        HttpHeaders headers = serverHttpRequest.getHeaders();
        String sessionid = headers.getFirst("sec-websocket-key");
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
        HttpSession session = servletRequest.getServletRequest().getSession();
        map.put("sessionId", session.getId());
        if (StrUtil.isNotBlank(sessionid)) {
            // 放入属性域
            map.put("token", sessionid);
            System.out.println("用户 token " + sessionid + " 握手成功！");
            return true;
        }

        System.out.println("用户登录已失效");
        return false;
    }
    /*
    握手后
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("握手完成");
    }
}
