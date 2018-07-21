package com.eletroinfo.eletroinfo.comparator.entitie;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
public class User {

    private Long id;

    private String name;

    private String email;

    private String login;

    private String password;

    private List<GroupUser> listGroupUsers;




}
