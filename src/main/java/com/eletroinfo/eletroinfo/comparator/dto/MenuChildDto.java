package com.eletroinfo.eletroinfo.comparator.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bruno Costa
 */

public class MenuChildDto {

    private Long id;
    private String name;
    private String url;
    private Long menuTopId;
    private String icon;
    private String tag;
    private boolean internalMenu = false;
    private String internationalizationCode;
    private Set<FeatureDto> featureDtoList = new HashSet<>();

    public MenuChildDto(Long id, String name, String url, Long menuTopId, String icon, String tag, boolean internalMenu, String internationalizationCode) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.menuTopId = menuTopId;
        this.icon = icon;
        this.tag = tag;
        this.internalMenu = internalMenu;
        this.internationalizationCode = internationalizationCode;
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

    public Long getMenuTopId() {
        return menuTopId;
    }

    public void setMenuTopId(Long menuTopId) {
        this.menuTopId = menuTopId;
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

    public Set<FeatureDto> getFeatureDtoList() {
        return featureDtoList;
    }

    public void setFeatureDtoList(Set<FeatureDto> featureDtoList) {
        this.featureDtoList = featureDtoList;
    }
}
