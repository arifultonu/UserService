package com.scube.dev.UserService.controller;

import com.scube.dev.UserService.payload.RoleDto;
import com.scube.dev.UserService.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleController {
    @Autowired
    RoleService roleService;

    @Operation(summary = "This is to save a Role in the Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved Role from Db.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping
    public RoleDto saveRole(@Valid @RequestBody RoleDto roleDto) {
        log.info("Inside the saveRole Controller");
        return roleService.saveRole(roleDto);
    }


    @Operation(summary = "This is to fetch all Roles from Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all Roles from Db",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping
    public List<RoleDto> getAllRole() {
        log.info("Inside the getAllRole Controller");
        return roleService.getAllRole();
    }

    @Operation(summary = "This is to fetch unique Role stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched Role form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Role not found with id : roleId",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findRoleById(@PathVariable(name = "id") long roleId) {
        log.info("Inside the findRoleById Controller");
        RoleDto roleDtoResponse = roleService.findRoleById(roleId);
        return new ResponseEntity<>(roleDtoResponse, HttpStatus.OK);
    }
}
