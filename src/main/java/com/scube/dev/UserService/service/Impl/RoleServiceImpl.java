package com.scube.dev.UserService.service.Impl;

import com.scube.dev.UserService.entity.Role;
import com.scube.dev.UserService.exception.ResourceNotFoundException;
import com.scube.dev.UserService.payload.RoleDto;
import com.scube.dev.UserService.repository.RoleRepository;
import com.scube.dev.UserService.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl  implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public RoleDto saveRole(RoleDto roleDto) {

        //convert dto to entity
        Role role = mapToEntity(roleDto);
        Role newRole = roleRepository.save(role);

        //convert Entity to dto
        RoleDto newRoleDto = mapToRoleDto(newRole);

        log.info("Inside saveRole of RoleService");

        return newRoleDto;
    }

    @Override
    public List<RoleDto> getAllRole() {

        List<Role> roleList = roleRepository.findAll();
        log.info("Inside getAllRole of RoleService");
        return  roleList.stream().map(role -> mapToRoleDto(role)).collect(Collectors.toList());
    }

    @Override
    public RoleDto findRoleById(long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role","id",roleId));
        log.info("Inside findRoleById of RoleService");
        return mapToRoleDto(role);
    }

    public RoleDto mapToRoleDto(Role role){
        RoleDto roleDto = modelMapper.map(role,RoleDto.class);

        return  roleDto;
    }

    public Role mapToEntity(RoleDto roleDto){
        Role role = modelMapper.map(roleDto,Role.class);

        return  role;
    }
}
