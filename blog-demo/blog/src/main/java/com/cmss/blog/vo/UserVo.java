package com.cmss.blog.vo;

import lombok.Data;

@Data
public class UserVo {

    private Long id;

    private String account;

    private String password;

    private String nickname;
}
