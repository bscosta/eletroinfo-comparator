package com.eletroinfo.eletroinfo.comparator.cptr.service.impl;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Item;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.ItemRepository;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<Item> saveList(List<Item> items) {
        List<Item> itemsSaved = new ArrayList<>();
        for (Item item : items) {
            if (item.getId() == null) {
                item.setStatus("OFERTADO");
                item = this.itemRepository.save(item);
            }
            itemsSaved.add(item);
        }

        return itemsSaved;
    }
}
