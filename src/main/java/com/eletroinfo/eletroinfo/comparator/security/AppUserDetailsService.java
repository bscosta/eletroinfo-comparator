package com.eletroinfo.eletroinfo.comparator.security;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOp = userService.findByLogin(login);
        User user = userOp.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
        return null;
    }

    /*private Collection<? extends GrantedAuthority> getPermissoes(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        //Listando as permissões do usuário
        //List<String> permissions =
    }*/
}
