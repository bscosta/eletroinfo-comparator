package com.eletroinfo.eletroinfo.comparator.listeners;

import com.eletroinfo.eletroinfo.comparator.entitie.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Bruno Costa
 */

public class BaseEntityListener {

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        //baseEntity.setUserRegister(authenticationFacade().getAuthenticatedUser().getId());
        //baseEntity.setUserLastUpdate(authenticationFacade().getAuthenticatedUser().getId());
        //baseEntity.setDateRegister(LocalDateTime.now(ZoneId.of(authenticationFacade().getAuthenticatedUser().getZone())));
        //baseEntity.setDateLastUpdate(LocalDateTime.now(ZoneId.of(authenticationFacade().getAuthenticatedUser().getZone())));
        //baseEntity.setZoneRegister(ZoneId.of(authenticationFacade().getAuthenticatedUser().getZone()));
        //baseEntity.setZoneLastUpdate(ZoneId.of(authenticationFacade().getAuthenticatedUser().getZone()));
    }

    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {

    }
}
