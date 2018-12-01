package xyz.huanxicloud.autoshop.controller;

import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.autoshop.common.ReturnMessage;
import xyz.huanxicloud.autoshop.controller.websocketcontroller.WebSocketController;
import xyz.huanxicloud.autoshop.dao.pojo.Device;

import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("send")
    public ReturnMessage sendMsg(String deviceId, String msg) {
        Session session = WebSocketController.getClients().get(deviceId);
        if (session == null)
            return new ReturnMessage(1, "设备不存在，或设备离线");
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ReturnMessage(0, "发送成功");
    }

    @RequestMapping("online")
    public ReturnMessage online() {
        return new ReturnMessage(1, "当前在线设备" + WebSocketController.getClients().size());
    }
}
