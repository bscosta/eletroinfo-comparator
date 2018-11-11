package com.eletroinfo.eletroinfo.comparator.repository.custom;

import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface MenuRepositoryCustom {

    /**
     *
     * @param name
     * @param menuTopName
     * @param pageable
     * @return
     */
    PageImpl<Menu> findByParameters(String name, String menuTopName, Pageable pageable);
}
