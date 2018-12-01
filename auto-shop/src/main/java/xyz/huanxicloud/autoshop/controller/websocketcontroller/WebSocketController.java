package xyz.huanxicloud.autoshop.controller.websocketcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/device/talk")
@Controller
public class WebSocketController {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Session> clients = new ConcurrentHashMap<>();
    private WebSocketMessageHandler messegeHandler = new WebSocketMessageHandler();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getDeviceId(Session session) {
        Principal device = (Principal) session.getUserPrincipal();
        if (device != null)
            return device.getName();
        return "";
    }

    @OnOpen
    public void onOpen(Session session) {
        if (clients.get(getDeviceId(session)) != null) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return;
            }
        }
        clients.put(getDeviceId(session), session);
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        //消息处理
        messegeHandler.doHandle(message, session);
    }

    @OnClose
    public void OnClose(Session session, CloseReason reason) {
        logger.info("客户端断开连接");
        clients.remove(getDeviceId(session));
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("客户端异常退出");
    }

    public static ConcurrentHashMap<String, Session> getClients() {
        return clients;
    }
}
