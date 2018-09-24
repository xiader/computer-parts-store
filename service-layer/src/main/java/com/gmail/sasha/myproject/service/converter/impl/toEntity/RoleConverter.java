package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Permission;
import com.gmail.sasha.myproject.dao.model.Role;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.PermissionDTO;
import com.gmail.sasha.myproject.service.model.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("roleEntityConverter")
public class RoleConverter implements EntityConverter<RoleDTO, Role> {


    @Autowired
    @Qualifier("permissionEntityConverter")
    private EntityConverter<PermissionDTO, Permission> permissionEntityConverter;

    @Override
    public Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setPermissions(permissionEntityConverter.toEntitySet(dto.getPermissions()));
        return role;
    }
}
