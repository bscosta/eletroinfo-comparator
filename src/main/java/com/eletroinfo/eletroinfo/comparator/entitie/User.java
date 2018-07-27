package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converter.ZoneIdConverter;
import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "group_id")})
    private List<GroupUser> listGroupUsers;

    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_registration", nullable = false)
    private User userRegistration;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_last_update", nullable = false)
    private User userLastUpdate;

    @Column(name = "date_last_update", nullable = false)
    private LocalDateTime dateLastUpdate;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "zone_register", nullable = false)
    private ZoneId zoneRegister;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "zone_last_update", nullable = false)
    private ZoneId zoneLastUpdate;

    @Column(name = "ip_register", nullable = false)
    private String ipRegister;

    @Column(name = "ip_last_update", nullable = false)
    private String ipLastUpdate;

    public Boolean isNovo() {
        return id == null;
    }

    public User() {
    }

    public User(String name, String email, String login, String password, UserType userType, boolean deleted) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.userType = userType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GroupUser> getListGroupUsers() {
        return listGroupUsers;
    }

    public void setListGroupUsers(List<GroupUser> listGroupUsers) {
        this.listGroupUsers = listGroupUsers;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(User userRegistration) {
        this.userRegistration = userRegistration;
    }

    public User getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(User userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
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
