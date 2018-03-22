package com.cherkasov.stockcar.service;

import com.cherkasov.stockcar.entity.Feature;
import com.cherkasov.stockcar.entity.FeatureValue;
import com.cherkasov.stockcar.mapper.FeatureValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "featureValueService")
public class FeatureValueService {

    @Autowired
    FeatureValueMapper featureValueMapper;

    /**
     * Получаем список характеристик по uid детали
     * @param id_component
     * @return список характеристик
     */
    public List<FeatureValue> getFeatureComponent(int id_component){
        return featureValueMapper.selectFeatureByComponent(id_component);
    }

}
