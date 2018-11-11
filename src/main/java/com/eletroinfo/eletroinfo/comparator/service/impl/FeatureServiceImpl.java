package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Feature;
import com.eletroinfo.eletroinfo.comparator.repository.FeatureRepository;
import com.eletroinfo.eletroinfo.comparator.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<Feature> saveList(List<Feature> features) {
        List<Feature> featuresSaved = new ArrayList<>();
        for (Feature feature : features) {
            if (feature.getId() == null) {
                feature = this.featureRepository.save(feature);
            }
            featuresSaved.add(feature);
        }
        return featuresSaved;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<Feature> feature = this.featureRepository.findById(id);
        if (feature.isPresent()) {
            feature.get().setDeleted(true);
            this.featureRepository.save(feature.get());
        }
    }
}
