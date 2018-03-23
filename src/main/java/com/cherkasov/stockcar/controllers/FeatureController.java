package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.Feature;
import com.cherkasov.stockcar.entity.FeatureValue;
import com.cherkasov.stockcar.service.FeatureValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/component/{id_component}", method = RequestMethod.GET)
    public ResponseEntity<List<FeatureValue>> getFeatureComponent(@PathVariable int id_component){
        return new ResponseEntity<>(featureValueService.getFeatureComponent(id_component), HttpStatus.OK);
    }

    /**
     * Добавляем новую характеристику
     * @param name наименование характеристики
     * @return ответ HTTP запроса на добавлание новой характеристики
     */
    @RequestMapping(value = "/add", params = {"name"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addFeature(@RequestParam String name){
        try{
            featureValueService.addNewFeature(name);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Удаляем запись об характеристике
     * @param id uid характеристики
     * @return ответ HTTP запроса об удалении записи
     */
    @RequestMapping(value = "/delete", params = {"id"})
    public ResponseEntity<Void> deleteFeature(@RequestParam int id){
        try{
            featureValueService.deleteFeature(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Добавление существующей характеристики к детали
     * @param feature uid характеристики
     * @param component uid детали
     * @param value значение
     * @return ответ HTTP запроса на добавление характеристики
     */
    @RequestMapping(value = "/addfeature", params = {"feature", "component", "value"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addFeatureComponent(int feature, int component, String value){
        try{
            featureValueService.addFeatureInComponent(feature, component, value);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
