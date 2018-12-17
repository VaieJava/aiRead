package com.outdd.aiRead.common.filterAndListener;

import com.outdd.aiRead.bam.user.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO:用户校验器
 * @author vaie
 * @Created 2018/12/17 10:39
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            User favUser= new User();
            favUser.setLoginName("favccxx");
            favUser.setPassword("favccxx");
            Collection<GrantedAuthority> authList = getAuthorities();
            userDetails = new org.springframework.security.core.userdetails.User(username, favUser.getPassword().toLowerCase(),true,true,true,true,authList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    /**  * 获取用户的角色权限,为了降低实验的难度，这里去掉了根据用户名获取角色的步骤     * @param    * @return   */
    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}