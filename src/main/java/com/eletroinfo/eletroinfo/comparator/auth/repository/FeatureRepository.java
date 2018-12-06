package com.eletroinfo.eletroinfo.comparator.auth.repository;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Costa
 */

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
}
