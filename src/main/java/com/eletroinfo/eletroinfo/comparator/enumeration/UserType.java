package com.eletroinfo.eletroinfo.comparator.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

public enum UserType {

    DEVELOPER(0),
    SUPERUSER(1),
    ADMINISTRATOR(2),
    OPERATIONAL(3),
    CLIENT(4),
    ADMINISTRATIVE(5);

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
