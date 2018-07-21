package com.eletroinfo.eletroinfo.comparator.entitie;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
public class GroupUser {

    private Long id;

    private String name;

    private String description;

    private List<User> listUsers;

    private boolean deleted;


}
