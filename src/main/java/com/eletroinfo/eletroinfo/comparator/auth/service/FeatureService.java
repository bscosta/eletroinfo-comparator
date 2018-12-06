package com.eletroinfo.eletroinfo.comparator.auth.service;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.Feature;

import java.util.List;

public interface FeatureService {

    List<Feature> saveList(List<Feature> features);

    void delete(Long id);
}
