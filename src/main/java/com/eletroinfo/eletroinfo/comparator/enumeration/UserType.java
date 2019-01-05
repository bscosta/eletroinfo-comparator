package com.eletroinfo.eletroinfo.comparator.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

public enum UserType {

    desenvolvedor(0),
    superusuario(1),
    administrador(2),
    lojista(3),
    cliente(4),
    vendedor(5);

    private final int valueUserType;

    private UserType(int value) {
        valueUserType = value;
    }

    public int getValueUserType() {
        return valueUserType;
    }

    public static UserType[] getValuesByUserType(int userType){
        List<UserType> types = new ArrayList<UserType>();
        for(UserType ut : UserType.values()){

            if(ut.valueUserType >= userType){
                types.add(ut);
                continue;
            }

        }

        UserType[] typeArray = (UserType[]) types.toArray(new UserType[types.size()]);
        return typeArray;
    }
}
