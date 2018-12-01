package xyz.huanxicloud.autoshop.controller.websocketcontroller;

import com.alibaba.fastjson.JSON;

import javax.websocket.Session;
import java.util.Map;

public class WebSocketMessageHandler {
    public void doHandle(String message, Session session) {
        //分发请求
        Map<String, String> msg = null;
        try {
            msg = (Map<String, String>) JSON.parse(message);
        } catch (Exception e) {
            //非map请求
        }
        String action = msg.get("action");
        System.out.println(action);

    }
}
