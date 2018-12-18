package com.outdd.aiRead.bam.user.service.impl;

import com.outdd.aiRead.bam.user.dao.UserMapper;
import com.outdd.aiRead.bam.user.pojo.User;
import com.outdd.aiRead.bam.user.service.UserApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
