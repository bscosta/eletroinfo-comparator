package com.eletroinfo.eletroinfo.comparator.config;

import com.eletroinfo.eletroinfo.comparator.security.AppUserDetailsService;
import com.eletroinfo.eletroinfo.comparator.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordUtils().passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/images/**")
                .antMatchers("/javascripts/**")
                .antMatchers("/stylesheets/**");
    }

    /*qualquer requisicao autenticada, pagina login e bloqueio personalizadas
	retira logout da criptografia csrf, apenas uma sessão por usuario*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/usuario/novo").hasAuthority("cadastrar.usuario")
                    .antMatchers(HttpMethod.PUT, "/usuario/{\\+d}").hasAuthority("editar.usuario")
                    .antMatchers(HttpMethod.GET,"/usuario/buscar").hasAuthority("buscar.usuario")
                    .antMatchers(HttpMethod.GET,"/usuario/{\\+d}").hasAuthority("buscar.usuario")
                    .antMatchers(HttpMethod.DELETE, "/usuario/{\\+d}").hasAuthority("deletar.usuario")
                    .anyRequest().authenticated()
                    .and()
                .requestCache()
                    .requestCache(new NullRequestCache())//remove a sessão do cache
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
                .and()
                .sessionManagement()
                    .maximumSessions(1)
                    .expiredUrl("/login");
    }
}
