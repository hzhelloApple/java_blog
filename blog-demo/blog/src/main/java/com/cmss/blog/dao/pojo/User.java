package com.cmss.blog.dao.pojo;


import lombok.Data;

@Data
public class User {

    private Long id;

    private String account;

    private String password;

    private String nickname;


}
