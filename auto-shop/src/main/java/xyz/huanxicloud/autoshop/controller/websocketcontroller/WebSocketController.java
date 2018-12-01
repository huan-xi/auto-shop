package xyz.huanxicloud.autoshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/device/talk")
@Controller
public class WebSocketController {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Session> clients = new ConcurrentHashMap<>();
    //    MessageHandlerService messageHandlerService = new MessageHandlerServiceImpl();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @OnOpen
    public void onOpen(Session session) {
        UserDetails device= (UserDetails) session.getUserPrincipal();
        System.out.println(device.getUsername());
        clients.put("test", session);
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        //消息处理
//        messageHandlerService.doHandle(message, session);
    }

    @OnClose
    public void OnClose(Session session, CloseReason reason) {
        logger.info("客户端断开连接");

    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("客户端异常退出");
    }

    public static ConcurrentHashMap<String, Session> getClients() {
        return clients;
    }
}
