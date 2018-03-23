package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.Component;
import com.cherkasov.stockcar.service.ComponentService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auto")
public class AutoController {

    /**
     * Автомобиль считается конечной деталью, включающая в себя составные детали
     * uid автомобиля - это uid детали (запись с полем ending = true)
     */

    @Autowired
    ComponentService componentService;
    
    /**
     * Получаем список автомобилей
     * @return список авто
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Component>> getAllAuto(){
        try {
            return new ResponseEntity<>(componentService.getAllAuto(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получаем автомобиль со списком деталей
     * @param id_auto автомобиля
     * @return автомобиль со списком деталей
     */
    @RequestMapping(value = "/{id_auto}")
    public ResponseEntity<Component> getAutoWithComponents(@PathVariable int id_auto){
        return new ResponseEntity<>(componentService.getAutoWithComponents(id_auto), HttpStatus.OK);
    }

    /**
     * Добавляем запись об новом автомобиле
     * @param name название автомобиля
     * @return статус Http запроса на добавление нового автомобиля
     */
    @RequestMapping(value = "/add", params = {"name"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addNewAuto(@RequestParam String name){
        try {
            componentService.addNewAuto(name);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Удаляем запись с машиной по uid
     * @param id uid автомобиля
     * @return статус HTTP запроса на удаление автомобиля
     */
    @RequestMapping(value = "/delete", params = {"id"}, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAuto(@RequestParam int id){
        try{
            //Если машина бабахаем ее
            if(componentService.getComponentById(id).isEnding()){
                componentService.deleteComponent(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
