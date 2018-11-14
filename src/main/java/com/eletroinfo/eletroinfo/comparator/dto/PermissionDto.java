package com.eletroinfo.eletroinfo.comparator.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionDto {

    private UserDto userDto;

    private GroupUserDto groupUserDto;

    private List<ParentMenuDto> parentMenusDto;

    private Set<Long> listConsultedsMenusChildIds = new HashSet<>();
    private Set<Long> listConsultedsFeaturesIds = new HashSet<>();


    private Set<Long> listSavedIds;

    public PermissionDto() {

    }

    public PermissionDto(UserDto userDto, List<ParentMenuDto> parentMenusDto) {
        this.userDto = userDto;
        this.parentMenusDto = parentMenusDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public GroupUserDto getGroupUserDto() {
        return groupUserDto;
    }

    public void setGroupUserDto(GroupUserDto groupUserDto) {
        this.groupUserDto = groupUserDto;
    }

    public List<ParentMenuDto> getParentMenusDto() {
        return parentMenusDto;
    }

    public void setParentMenusDto(List<ParentMenuDto> parentMenusDto) {
        this.parentMenusDto = parentMenusDto;
    }

    public Set<Long> getListConsultedsMenusChildIds() {
        return listConsultedsMenusChildIds;
    }

    public void setListConsultedsMenusChildIds(Set<Long> listConsultedsMenusChildIds) {
        this.listConsultedsMenusChildIds = listConsultedsMenusChildIds;
    }

    public Set<Long> getListConsultedsFeaturesIds() {
        return listConsultedsFeaturesIds;
    }

    public void setListConsultedsFeaturesIds(Set<Long> listConsultedsFeaturesIds) {
        this.listConsultedsFeaturesIds = listConsultedsFeaturesIds;
    }

    public Set<Long> getListSavedIds() {
        return listSavedIds;
    }

    public void setListSavedIds(Set<Long> listSavedIds) {
        this.listSavedIds = listSavedIds;
    }
}
