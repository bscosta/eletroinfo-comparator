package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.repository.UserRepository;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());

        if(userExist.isPresent() && !userExist.get().equals(user)) {
            return user;
        /*
        ** Melhorar isso aqui, criar uma exception para informar que o usuário é repetido,
        ** ou criar um validator.
        */
        }

        if (user.isNovo() && StringUtils.isEmpty(user.getPassword())) {
            return user;
        /*
         ** Melhorar isso aqui, criar uma exception para informar que o usuário é repetido,
         ** ou criar um validator.
         */
        } else if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(userExist.get().getPassword());
        }

        return userRepository.save(user);
    }
}
