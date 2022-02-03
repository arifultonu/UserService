package com.scube.dev.UserService.payload;

import lombok.Data;

@Data
public class UserDto {

    private long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private String approveBy;

    private String updateBy;

    private String status;

    private String orgType;

}
