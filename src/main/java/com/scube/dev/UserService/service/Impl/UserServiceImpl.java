package com.scube.dev.UserService.service.Impl;

import com.scube.dev.UserService.entity.User;
import com.scube.dev.UserService.exception.ResourceNotFoundException;
import com.scube.dev.UserService.payload.UserDto;
import com.scube.dev.UserService.repository.UserRepository;
import com.scube.dev.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto updateUser(UserDto userDto, long id) {
        //get user by id from the database
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));

        User updatedUser = mapToEntity(userDto);
        updatedUser.setId(id);

        updatedUser = userRepository.save(updatedUser);

        return mapToDto(updatedUser);
    }



    //convert DTO to Entity
    private User mapToEntity(UserDto userDto){

        User user = modelMapper.map(userDto,User.class);

        return user;
    }

    //convert entity to dto
    private UserDto mapToDto(User user){

        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }
}
