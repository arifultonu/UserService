package com.scube.dev.UserService.service;

import com.scube.dev.UserService.payload.UserDto;

public interface UserService {

    UserDto updateUser(UserDto userDto, long id);
}
