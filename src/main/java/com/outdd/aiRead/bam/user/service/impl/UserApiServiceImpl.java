package com.outdd.aiRead.bam.user.service.impl;

import com.outdd.aiRead.bam.user.dao.UserMapper;
import com.outdd.aiRead.bam.user.pojo.User;
import com.outdd.aiRead.bam.user.service.UserApiService;
import com.outdd.aiRead.common.util.CommomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author vaie
 * @Created 2018/12/18 11:30
 */
@Service
public class UserApiServiceImpl implements UserApiService {
    @Resource
    UserMapper userMapper;

    @Override
    public User selectByPrimaryName(String userName) {
        return userMapper.selectByPrimaryName(userName);
    }

    @Override
    public Integer addUser(User user) {
        user.setUserId(CommomUtil.uuid());
        user.setGenTime(new Date());
        return userMapper.insert(user);
    }
}
