package com.eletroinfo.eletroinfo.comparator.security;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Bruno Costa
 */

public class UserLogged extends User {

    private static final long serialVersionUID = 1L;

    private ZoneId zoneId;

    private String ipAddr;

    private PermissionDto permissionDto;

    public UserLogged(PermissionDto permissionDto, List<GrantedAuthority> authorities) {
        super(permissionDto.getUserDto().getLogin(), permissionDto.getUserDto().getPassword(), authorities);
        this.permissionDto = permissionDto;
    }

    public PermissionDto getPermissionDto() {
        return permissionDto;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
