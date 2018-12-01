package xyz.haunxicloud.autoshop.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.haunxicloud.autoshop.common.JwtTokenUtil;
import xyz.haunxicloud.autoshop.common.ReturnMessage;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private UserDetailsService userDetailsService;
    @PostMapping("/login")
    public ReturnMessage login(String deviceId, String secretKey) {
        if (StringUtils.isEmpty(deviceId) || StringUtils.isEmpty(secretKey))
            return new ReturnMessage(2, "设备ID或秘钥不能为空");
        if (deviceId.equals("test") && secretKey.equals("test")) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(deviceId);
            return new ReturnMessage(1, "auto_shop" + JwtTokenUtil.generateToken(userDetails));
        }
        return new ReturnMessage(0, "账号或密码错误");
    }
}
