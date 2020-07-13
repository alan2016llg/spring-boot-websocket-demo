package com.example.websocketdemo.websocket.stomptype;

import lombok.Data;

/**
 * @Author: liulg
 * @Date: 2020/7/12 22:28
 */
@Data
public class Conver {
    /*
    消息内容
     */
    private String content;
    //用户唯一标识
    private String userid;
}
