package com.eletroinfo.eletroinfo.comparator.security;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserLogged extends User {

    private static final long serialVersionUID = 1L;

    private PermissionDto permissionDto;

    public UserLogged(PermissionDto permissionDto, List<GrantedAuthority> authorities) {
        super(permissionDto.getUserDto().getLogin(), permissionDto.getUserDto().getPassword(), authorities);
        this.permissionDto = permissionDto;
    }

    public PermissionDto getPermissionDto() {
        return permissionDto;
    }
}
