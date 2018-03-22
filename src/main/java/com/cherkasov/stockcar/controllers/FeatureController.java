package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.FeatureValue;
import com.cherkasov.stockcar.service.FeatureValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {

    @Autowired
    FeatureValueService featureValueService;

    /**
     * Получаем список характеристик и значения
     * @param id_component
     * @return список характеристик детали
     */

    @RequestMapping("/component/{id_component}")
    public List<FeatureValue> getFeatureComponent(@PathVariable int id_component){
        return featureValueService.getFeatureComponent(id_component);
    }

}
