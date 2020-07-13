package com.example.websocketdemo.websocket;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liulg
 * @Date: 2020/7/11 15:24
 */
@RestController
@CrossOrigin
public class WebController {
    @RequestMapping("/websocket")
    public String web(HttpServletResponse response, HttpServletRequest request){
       // setCrossDomain(response,request);
        return "start websocket";
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
