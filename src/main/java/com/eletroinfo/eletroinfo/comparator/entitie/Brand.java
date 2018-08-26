package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
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

    public Boolean isNovo() {
        return this.id == null;
    }

    public Brand() {

    }

    public Brand(String name, String description, boolean deleted) {
        this.name = name;
        this.description = description;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDeleted() {
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
