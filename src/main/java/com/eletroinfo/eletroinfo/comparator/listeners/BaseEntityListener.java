package com.eletroinfo.eletroinfo.comparator.listeners;

import com.eletroinfo.eletroinfo.comparator.dto.UserDto;
import com.eletroinfo.eletroinfo.comparator.entitie.BaseEntity;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.User;
import com.eletroinfo.eletroinfo.comparator.infra.RunTimeSession;
import com.eletroinfo.eletroinfo.comparator.security.UserLogged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * @author Bruno Costa
 */

public class BaseEntityListener {

    @Autowired
    private RunTimeSession runTimeSession;

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        UserLogged userLogged = (UserLogged) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        baseEntity.setUserRegister(convertUserDtoToUserEntity(userLogged.getPermissionDto().getUserDto()));
        baseEntity.setUserLastUpdate(convertUserDtoToUserEntity(userLogged.getPermissionDto().getUserDto()));
        baseEntity.setDateRegister(LocalDateTime.now(userLogged.getZoneId()));
        baseEntity.setDateLastUpdate(LocalDateTime.now(userLogged.getZoneId()));
        baseEntity.setZoneRegister(userLogged.getZoneId());
        baseEntity.setZoneLastUpdate(userLogged.getZoneId());
        baseEntity.setIpRegister(userLogged.getIpAddr());
        baseEntity.setIpLastUpdate(userLogged.getIpAddr());
    }

    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        UserLogged userLogged = (UserLogged) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        baseEntity.setUserLastUpdate(convertUserDtoToUserEntity(userLogged.getPermissionDto().getUserDto()));
        baseEntity.setDateLastUpdate(LocalDateTime.now(userLogged.getZoneId()));
        baseEntity.setZoneLastUpdate(userLogged.getZoneId());
        baseEntity.setIpLastUpdate(userLogged.getIpAddr());
    }

    private User convertUserDtoToUserEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        return user;
    }
}
