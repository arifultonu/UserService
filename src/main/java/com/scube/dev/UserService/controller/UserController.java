package com.scube.dev.UserService.controller;

import com.scube.dev.UserService.entity.User;
import com.scube.dev.UserService.payload.UserDto;
import com.scube.dev.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                           @PathVariable(name = "id") long id){

            UserDto updatedUser = userService.updateUser(userDto, id);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
