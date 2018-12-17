package com.outdd.aiRead.bam.user.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private String userId;

    private String loginName;

    private String password;

    private String vsername;

    private String mobile;

    private String email;

    private Date genTime;

    private Date loginTime;

    private Date lastLoginTime;

    private Integer loginCount;

    private String viaIcon;

    private static final long serialVersionUID = 1L;


}