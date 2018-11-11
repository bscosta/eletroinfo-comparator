package com.eletroinfo.eletroinfo.comparator.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

public class ParentMenuDto {

    private Long id;
    private String name;
    private String icon;
    private String tag;
    private boolean internalMenu = false;
    private String internationalizationCode;
    private List<MenuChildDto> menuChildDtoList = new ArrayList<>();

    public ParentMenuDto(Long id, String name, String icon, String tag, boolean internalMenu, String internationalizationCode) {
        this.id = id;
        this.name = name;
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

    public List<MenuChildDto> getMenuChildDtoList() {
        return menuChildDtoList;
    }

    public void setMenuChildDtoList(List<MenuChildDto> menuChildDtoList) {
        this.menuChildDtoList = menuChildDtoList;
    }
}
