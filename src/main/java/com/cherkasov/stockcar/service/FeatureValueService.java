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

    /**
     * Добавляем новую характеристику
     * @param name наименование характеристики
     */
    public void addNewFeature(String name) {
        featureValueMapper.addNewFeature(name);
    }

    /**
     * Удаление записи характеристики
     * @param id_feature uid характеристики
     */
    public void deleteFeature(int id_feature) {
        featureValueMapper.deleteFeature(id_feature);
    }

    /**
     * Получение характеристики по uid
     * @param id_feature uid характеристики
     * @return характеристика
     */
    public Feature getFeatureById(int id_feature){
        return featureValueMapper.selectFeatureById(id_feature);
    }

    /**
     * Получение списка характеристик
     * @return характеристики
     */
    public List<Feature> getAllFeature() {
        return featureValueMapper.selectAllFeature();
    }

    /**
     * Добавление характеристики детали
     * @param id_feature uid характеристики
     * @param id_component uid детали
     * @param value значение детали
     */
    public void addFeatureInComponent(int id_feature, int id_component, String value) {
        featureValueMapper.addFeatureComponent(id_feature, id_component, value);
    }
}
