package com.eletroinfo.eletroinfo.comparator.config;

import com.eletroinfo.eletroinfo.comparator.security.AppUserDetailsService;
import com.eletroinfo.eletroinfo.comparator.security.Securityhandler;
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

/**
 * @author Bruno Costa
 */

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Securityhandler successHandler;

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
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario/novo").hasAuthority("cadastrar.usuario")
                .antMatchers(HttpMethod.POST, "/usuario/{\\+d}").hasAuthority("editar.usuario")
                .antMatchers(HttpMethod.GET,"/usuario/buscar").hasAuthority("buscar.todos.usuarios")
                .antMatchers(HttpMethod.GET,"/usuario/{\\+d}").hasAuthority("buscar.usuario")
                .antMatchers(HttpMethod.DELETE, "/usuario/{\\+d}").hasAuthority("deletar.usuario")

                .antMatchers(HttpMethod.POST, "/grupoUsuarios/novo").hasAuthority("cadastrar.grupo.usuarios")
                .antMatchers(HttpMethod.POST, "/grupoUsuarios/{\\+d}").hasAuthority("editar.grupo.usuarios")
                .antMatchers(HttpMethod.GET,"/grupoUsuarios/buscar").hasAuthority("buscar.todos.grupos.usuarios")
                .antMatchers(HttpMethod.GET,"/grupoUsuarios/{\\+d}").hasAuthority("buscar.grupo.usuarios")
                .antMatchers(HttpMethod.DELETE, "/grupoUsuarios/{\\+d}").hasAuthority("deletar.grupo.usuarios")

                .antMatchers(HttpMethod.POST, "/menu/novo").hasAuthority("cadastrar.menu")
                .antMatchers(HttpMethod.POST, "/menu/{\\+d}").hasAuthority("editar.menu")
                .antMatchers(HttpMethod.GET,"/menu/buscar").hasAuthority("buscar.todos.menus")
                .antMatchers(HttpMethod.GET,"/menu/{\\+d}").hasAuthority("buscar.menu")
                .antMatchers(HttpMethod.DELETE, "/menu/{\\+d}").hasAuthority("deletar.menu")

                .antMatchers(HttpMethod.POST, "/marca/novo").hasAuthority("cadastrar.marca")
                .antMatchers(HttpMethod.POST, "/marca/{\\+d}").hasAuthority("editar.marca")
                .antMatchers(HttpMethod.GET,"/marca/buscar").hasAuthority("buscar.todas.marcas")
                .antMatchers(HttpMethod.GET,"/marca/{\\+d}").hasAuthority("buscar.marca")
                .antMatchers(HttpMethod.DELETE, "/marca/{\\+d}").hasAuthority("deletar.marca")

                .antMatchers(HttpMethod.POST, "/produto/novo").hasAuthority("cadastrar.produto")
                .antMatchers(HttpMethod.POST, "/produto/{\\+d}").hasAuthority("editar.produto")
                .antMatchers(HttpMethod.GET,"/produto/buscar").hasAuthority("buscar.todos.produtos")
                .antMatchers(HttpMethod.GET,"/produto/{\\+d}").hasAuthority("buscar.produto")
                .antMatchers(HttpMethod.DELETE, "/produto/{\\+d}").hasAuthority("deletar.produto")

                .antMatchers(HttpMethod.POST, "/vendedor/novo").hasAuthority("cadastrar.vendedor")
                .antMatchers(HttpMethod.POST, "/vendedor/{\\+d}").hasAuthority("editar.vendedor")
                .antMatchers(HttpMethod.GET,"/vendedor/buscar").hasAuthority("buscar.todos.vendedores")
                .antMatchers(HttpMethod.GET,"/vendedor/{\\+d}").hasAuthority("buscar.vendedor")
                .antMatchers(HttpMethod.DELETE, "/vendedor/{\\+d}").hasAuthority("deletar.vendedor")

                .antMatchers(HttpMethod.POST, "/fornecedor/novo").hasAuthority("cadastrar.fornecedor")
                .antMatchers(HttpMethod.POST, "/fornecedor/{\\+d}").hasAuthority("editar.fornecedor")
                .antMatchers(HttpMethod.GET,"/fornecedor/buscar").hasAuthority("buscar.todos.fornecedores")
                .antMatchers(HttpMethod.GET,"/fornecedor/{\\+d}").hasAuthority("buscar.fornecedor")
                .antMatchers(HttpMethod.DELETE, "/fornecedor/{\\+d}").hasAuthority("deletar.fornecedor")

                .antMatchers(HttpMethod.POST, "/orcamento/novo").hasAuthority("cadastrar.orcamento")
                .antMatchers(HttpMethod.POST, "/orcamento/{\\+d}").hasAuthority("editar.orcamento")
                .antMatchers(HttpMethod.GET,"/orcamento/buscar").hasAuthority("buscar.todos.orcamentos")
                .antMatchers(HttpMethod.GET,"/orcamento/{\\+d}").hasAuthority("buscar.orcamento")
                .antMatchers(HttpMethod.DELETE, "/orcamento/{\\+d}").hasAuthority("deletar.orcamento")
                .anyRequest().authenticated()
                .and()
            .requestCache()
                .requestCache(new NullRequestCache())//remove a sessão do cache
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .successHandler(successHandler)
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
