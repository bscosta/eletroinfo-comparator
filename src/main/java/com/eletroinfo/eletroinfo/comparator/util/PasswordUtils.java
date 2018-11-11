package com.eletroinfo.eletroinfo.comparator.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class PasswordUtils {

    public PasswordUtils() {
    }

    /**
     * Gera um hash utilizando o BCrypt.
     *
     * @param senha
     * @return String
     */
    public static String generateBCrypt(String senha) {
        if (senha == null) {
            return senha;
        }

        return new BCryptPasswordEncoder().encode(senha);
    }

    /**
     * Valida o Password
     * Password deve ter pelo menos 1 letra maiuscula, 1 letra minuscula e 1 numero
     *
     * @param password
     * @return boolean
     */
    public boolean validatePassword(String password){
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])");
        return pattern.matcher(password).find();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
