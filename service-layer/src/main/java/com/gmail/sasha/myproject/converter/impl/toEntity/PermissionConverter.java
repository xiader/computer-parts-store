package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.Permission;
import com.gmail.sasha.myproject.model.PermissionDTO;
import com.gmail.sasha.myproject.model.Role;
import com.gmail.sasha.myproject.model.RoleDTO;

public class PermissionConverter implements EntityConverter<PermissionDTO, Permission> {
    private EntityConverter<RoleDTO, Role> permissionEntityConverter = new RoleConverter();

    @Override
    public Permission toEntity(PermissionDTO dto) {
        if (dto == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        permission.setRoles(permissionEntityConverter.toEntitySet(dto.getRoles()));
        return permission;
    }
}
