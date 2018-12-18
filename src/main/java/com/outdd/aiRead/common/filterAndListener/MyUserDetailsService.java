package com.outdd.aiRead.common.filterAndListener;

import com.outdd.aiRead.bam.user.service.UserApiService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO:用户校验器
 * @author vaie
 * @Created 2018/12/17 10:39
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserApiService userApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            //1.通过用户名查询用户
            log.info("用户的用户名: {}", username);
            com.outdd.aiRead.bam.user.pojo.User u=userApiService.selectByPrimaryName(username);
             username=u.getLoginName();
            String password = passwordEncoder.encode(u.getPassword());
            log.info("password: {}", password);
            Collection<GrantedAuthority> authList = getAuthorities();
            // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
             user = new User(username, password,
                    authList);
        }catch (Exception e) {
        e.printStackTrace();
    }

        return user;

    }

    /**  * 获取用户的角色权限,为了降低实验的难度，这里去掉了根据用户名获取角色的步骤     * @param    * @return   */
    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}