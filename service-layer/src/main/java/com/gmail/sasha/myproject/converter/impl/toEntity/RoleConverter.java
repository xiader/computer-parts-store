package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.Permission;
import com.gmail.sasha.myproject.model.PermissionDTO;
import com.gmail.sasha.myproject.model.Role;
import com.gmail.sasha.myproject.model.RoleDTO;

public class RoleConverter implements EntityConverter<RoleDTO, Role> {

    @Override
    public Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        EntityConverter<PermissionDTO, Permission> permissionEntityConverter = new PermissionConverter();
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setPermissions(permissionEntityConverter.toEntitySet(dto.getPermissions()));
        return role;
    }
}
