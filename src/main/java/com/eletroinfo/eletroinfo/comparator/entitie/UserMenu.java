package com.eletroinfo.eletroinfo.comparator.entitie;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_menu")
public class UserMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name="menu_id")
    private Menu menuId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_menu_feature",
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
