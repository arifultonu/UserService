package com.scube.dev.UserService.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RoleDto {
    public long roleId;
    @NotEmpty
    @Size(min = 4, message = "Role Name should have 4 characters.")
    private String roleName;
}
