package com.eletroinfo.eletroinfo.comparator.entitie;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "menu")
public class Menu extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String url;

    @ManyToOne
    @JoinColumn(name="menu_top_id")
    private Menu menuTop;

    private String icon;

    @Column(name = "internal_menu")
    private boolean internalMenu;

    @Column(name = "internationalization_code")
    private String internationalizationCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "menu_feature",
            joinColumns = { @JoinColumn(name = "menu_id", table = "menu", referencedColumnName="id")},
            inverseJoinColumns = { @JoinColumn(name = "feature_id", table = "feature", referencedColumnName="id")})
    private List<Feature> features;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getMenuTop() {
        return menuTop;
    }

    public void setMenuTop(Menu menuTop) {
        this.menuTop = menuTop;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isInternalMenu() {
        return internalMenu;
    }

    public void setInternalMenu(boolean internalMenu) {
        this.internalMenu = internalMenu;
    }

    public String getInternationalizationCode() {
        return internationalizationCode;
    }

    public void setInternationalizationCode(String internationalizationCode) {
        this.internationalizationCode = internationalizationCode;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
