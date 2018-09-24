package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Permission;
import com.gmail.sasha.myproject.dao.model.Role;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.PermissionDTO;
import com.gmail.sasha.myproject.service.model.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("permissionEntityConverter")
public class PermissionConverter implements EntityConverter<PermissionDTO, Permission> {

    @Autowired
    @Qualifier("roleEntityConverter")
    private EntityConverter<RoleDTO, Role> roleEntityConverter;

    @Override
    public Permission toEntity(PermissionDTO dto) {
        if (dto == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        permission.setRoles(roleEntityConverter.toEntitySet(dto.getRoles()));
        return permission;
    }
}
