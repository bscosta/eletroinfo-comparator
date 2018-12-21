package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Item;

import java.util.List;

/**
 * @author Bruno Costa
 */

public interface ItemService {

    /**
     *
     * @param items
     * @return
     */
    List<Item> saveList(List<Item> items);

    /**
     *
     * @param id
     */
    void delete(Long id);
}
