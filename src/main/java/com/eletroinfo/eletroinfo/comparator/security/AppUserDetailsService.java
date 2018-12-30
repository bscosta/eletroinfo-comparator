package com.eletroinfo.eletroinfo.comparator.security;

import com.eletroinfo.eletroinfo.comparator.dto.MenuChildDto;
import com.eletroinfo.eletroinfo.comparator.dto.ParentMenuDto;
import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bruno Costa
 */

@Service
public class AppUserDetailsService implements UserDetailsService {

    static Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.info("Buscando permissões para o usuário com login {}", login);
        PermissionDto permissionDto = this.userPermissionService.findByPermissionForLogonByLogin(login);
        if (permissionDto == null) {
            log.info("Permissões não foram encontradas, erro nas credenciais para login {}", login);
            new UsernameNotFoundException("Usuário ou senha incorretos");
        }

        log.info("Permissões foram encontradas para login {}", login);
        List<GrantedAuthority> grantedAuthorities = this.getAuthorities(permissionDto);
        return new UserLogged(permissionDto, grantedAuthorities);
    }

    public List<GrantedAuthority> getAuthorities(PermissionDto permissionDto){
        log.info("Adicionando permissões para usuário logado com id {}", permissionDto.getUserDto().getId());
        List<GrantedAuthority> features = new ArrayList<>();
        for(ParentMenuDto menuFatherDto : permissionDto.getParentMenusDto()){
            for(MenuChildDto menuChildDto : menuFatherDto.getMenuChildDtoList()){
                features.addAll(menuChildDto.getFeatureDtoList().stream().map(featureDto -> new SimpleGrantedAuthority(featureDto.getCode())).collect(Collectors.toList()));

            }
        }
        return features;
    }
}
