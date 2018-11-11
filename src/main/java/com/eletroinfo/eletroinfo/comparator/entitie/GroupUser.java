package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "group_user")
@DynamicUpdate
public class GroupUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Basic(fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "group_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    private List<User> listUsers;

    public Boolean isNovo() {
        return this.id == null;
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

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public GroupUser() {
        super();
        this.listUsers = new ArrayList<>();
    }
}
