package com.eletroinfo.eletroinfo.comparator.dto;

import java.util.List;

public class PermissionDto {

    private UserDto userDto;

    private GroupUserDto groupUserDto;

    private List<ParentMenuDto> parentMenusDto;

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
}
