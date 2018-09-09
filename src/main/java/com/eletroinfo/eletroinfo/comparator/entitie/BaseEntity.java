package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;
import com.eletroinfo.eletroinfo.comparator.listeners.BaseEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Bruno Costa
 */

@EntityListeners(BaseEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @ManyToOne(optional = true, fetch= FetchType.LAZY)
    @JoinColumn(name = "user_register")
    private User userRegister;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_last_update")
    private User userLastUpdate;

    @Column(name = "date_register", nullable = false)
    private LocalDateTime dateRegister;

    @Column(name = "date_last_update", nullable = true)
    private LocalDateTime dateLastUpdate;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "zone_register", nullable = true)
    private ZoneId zoneRegister;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "zone_last_update", nullable = true)
    private ZoneId zoneLastUpdate;

    @Column(name = "ip_register", nullable = true)
    private String ipRegister;

    @Column(name = "ip_last_update", nullable = true)
    private String ipLastUpdate;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(User userRegister) {
        this.userRegister = userRegister;
    }

    public User getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(User userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public LocalDateTime getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public ZoneId getZoneRegister() {
        return zoneRegister;
    }

    public void setZoneRegister(ZoneId zoneRegister) {
        this.zoneRegister = zoneRegister;
    }

    public ZoneId getZoneLastUpdate() {
        return zoneLastUpdate;
    }

    public void setZoneLastUpdate(ZoneId zoneLastUpdate) {
        this.zoneLastUpdate = zoneLastUpdate;
    }
}
