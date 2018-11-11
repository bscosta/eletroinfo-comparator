package com.eletroinfo.eletroinfo.comparator.util.AjaxPagedSearch;

/**
 * @author Bruno Costa
 */

public class SearchAjaxDto {

    private Object objects;

    private int number;

    private Boolean isLast;

    public Object getObjects() {
        return objects;
    }

    public void setObjects(Object objects) {
        this.objects = objects;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }
}
