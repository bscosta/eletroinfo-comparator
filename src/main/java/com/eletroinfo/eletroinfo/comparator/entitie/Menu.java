package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.dto.FeatureDto;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "menu")
@DynamicUpdate
public class Menu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String url;

    @ManyToOne
    @JoinColumn(name="menu_top_id")
    private Menu menuTop;

    private String icon;

    private String tag;

    @Column(name = "internal_menu")
    private boolean internalMenu;

    @Column(name = "internationalization_code")
    private String internationalizationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_feature",
            joinColumns = { @JoinColumn(name = "menu_id")},
            inverseJoinColumns = { @JoinColumn(name = "feature_id")})
    private List<Feature> features = new ArrayList<>();

    @Transient
    private Boolean isUpdateFeature;

    public Menu(){
    }

    public Boolean isNovo() {
        return  this.id == null;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public Boolean getUpdateFeature() {
        return isUpdateFeature;
    }

    public void setUpdateFeature(Boolean updateFeature) {
        isUpdateFeature = updateFeature;
    }
}
