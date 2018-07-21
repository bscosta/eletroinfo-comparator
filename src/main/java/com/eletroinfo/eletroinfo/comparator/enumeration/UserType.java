package com.eletroinfo.eletroinfo.comparator.enumeration;

/**
 * @author Bruno Costa
 */

public enum UserType {

    DEVELOPER(0),
    SUPERUSER(1),
    ADMINISTRATOR(2),
    OPERATIONAL(5),
    CLIENT(6),
    ADMINISTRATIVE(7);

    private final int valueUserType;

    private UserType(int value) {
        valueUserType = value;
    }

    public int getValueUserType() {
        return valueUserType;
    }
}
