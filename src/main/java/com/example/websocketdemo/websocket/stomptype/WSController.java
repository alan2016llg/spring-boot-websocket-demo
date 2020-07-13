package com.example.websocketdemo.websocket.stomptype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liulg
 * @Date: 2020/7/12 21:06
 */
@Slf4j
@Controller
public class WSController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    public Conver sendMessage(Conver conver){

        log.info("客户端发送信息："+conver.getContent());
        return conver;
    }

    @GetMapping("/sendOneUser")
    public @ResponseBody
    Object sendMsgByUser(String token, String msg, HttpServletResponse response, HttpServletRequest request) {
        setCrossDomain(response,request);
        simpMessagingTemplate.convertAndSendToUser(token, "/msg", msg);
        return "success";
    }

    /*
跨域设置
*/
    public void setCrossDomain(HttpServletResponse response, HttpServletRequest request){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed", "1");
    }
}
