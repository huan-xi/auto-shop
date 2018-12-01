package xyz.huanxicloud.autoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.autoshop.common.JwtTokenUtil;
import xyz.huanxicloud.autoshop.common.ReturnMessage;
import xyz.huanxicloud.autoshop.dao.mapper.DeviceMapper;
import xyz.huanxicloud.autoshop.dao.pojo.Device;
import xyz.huanxicloud.autoshop.dao.pojo.Good;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    DeviceMapper deviceMapper;

    @PostMapping("/login")
    public ReturnMessage login(String deviceId, String secretKey) {
        if (StringUtils.isEmpty(deviceId) || StringUtils.isEmpty(secretKey))
            return new ReturnMessage(2, "设备ID或秘钥不能为空");
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        if (device != null && device.getSecretKey().equals(secretKey)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(deviceId);
            return new ReturnMessage(1, "auto_shop" + JwtTokenUtil.generateToken(userDetails));
        }
        return new ReturnMessage(0, "账号或密码错误");
    }

    private String getDeviceId(String token) {
        return null;
    }

    @PostMapping("/good")
    public ReturnMessage addGood(Good good) {
        return null;
    }
}
