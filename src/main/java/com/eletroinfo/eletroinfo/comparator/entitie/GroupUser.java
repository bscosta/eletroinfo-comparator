package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "group_user")
public class GroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "group_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    private List<User> listUsers;

    @Column(name = "deleted", length = 100)
    private boolean deleted;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_registrater")
    private User userRegistrater;

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

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUserRegistrater() {
        return userRegistrater;
    }

    public void setUserRegistrater(User userRegistrater) {
        this.userRegistrater = userRegistrater;
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

    public String getIpRegister() {
        return ipRegister;
    }

    public void setIpRegister(String ipRegister) {
        this.ipRegister = ipRegister;
    }

    public String getIpLastUpdate() {
        return ipLastUpdate;
    }

    public void setIpLastUpdate(String ipLastUpdate) {
        this.ipLastUpdate = ipLastUpdate;
    }
}
