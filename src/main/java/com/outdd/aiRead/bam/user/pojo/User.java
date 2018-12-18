package com.outdd.aiRead.bam.user.pojo;

import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
public class User {
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