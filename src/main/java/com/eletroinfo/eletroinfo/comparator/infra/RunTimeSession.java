package com.eletroinfo.eletroinfo.comparator.infra;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RunTimeSession {

    private ZoneId zoneId;
    private String ipAddres;

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getIpAddres() {
        return ipAddres;
    }

    public void setIpAddres(String ipAddres) {
        this.ipAddres = ipAddres;
    }
}
