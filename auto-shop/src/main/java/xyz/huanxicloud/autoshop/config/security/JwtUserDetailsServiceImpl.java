package xyz.haunxicloud.autoshop.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证方法
 *
 * @author hackyo
 * Created on 2017/12/8 9:18.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
//            权限管理去掉
//            user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            return new JwtUser(username, null, null);
        }
    }

}
