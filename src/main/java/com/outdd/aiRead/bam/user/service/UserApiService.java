package com.outdd.aiRead.bam.user.service;

import com.outdd.aiRead.bam.user.pojo.User;
import com.outdd.aiRead.common.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vaie
 * @Created 2018/12/18 11:30
 */
public interface UserApiService {
   //通过用户名查找用户
    User selectByPrimaryName(String userName);

    Integer addUser(User user);
}
