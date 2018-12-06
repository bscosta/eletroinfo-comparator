package com.eletroinfo.eletroinfo.comparator.auth.entitie;


import com.eletroinfo.eletroinfo.comparator.entitie.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_menu", schema = "auth")
public class UserMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User userId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="menu_id", nullable = false)
    private Menu menuId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_menu_feature", schema = "auth",
        joinColumns = { @JoinColumn(name = "user_menu_id")},
        inverseJoinColumns = { @JoinColumn(name = "feature_id")})
    private List<Feature> features;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
