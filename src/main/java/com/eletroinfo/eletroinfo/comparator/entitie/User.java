package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;
import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "user")
@DynamicUpdate
public class User extends BaseEntity implements Serializable {

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

    public Boolean isNovo() {
        return id == null;
    }

    public User() {
    }

    public User(String name, String email, String login, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.userType = userType;
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
}
