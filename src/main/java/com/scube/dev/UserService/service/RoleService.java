package com.scube.dev.UserService.service;

import com.scube.dev.UserService.payload.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto saveRole(RoleDto roleDto);

    List<RoleDto> getAllRole();

    RoleDto findRoleById(long categoryId);

}
