package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.Component;
import com.cherkasov.stockcar.service.AssambleService;
import com.cherkasov.stockcar.service.ComponentService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest контроллер деталей
 * @author scherkasov
 */

@RestController
@RequestMapping(value = "/api/component")
@CrossOrigin(origins = "http://localhost:4200")
public class ComponentController {

    @Autowired
    ComponentService componentService;

    @Autowired
    AssambleService assambleService;

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Получаем список всех деталей
     * @return Список всех компонентов без автомобилей
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Component>> getAllComponents(){
        return new ResponseEntity<>(componentService.getAllComponent(), HttpStatus.OK);
    }

    /**
     * Получаем деталь с нужным uid
     * @param id_component детали
     * @return строку с деталью и переводим в json
     */
    @RequestMapping(value = "/{id_component}", produces = "application/json")
    public ResponseEntity<String> getComponentById(@PathVariable int id_component){
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //игнорируем поля с null
            return new ResponseEntity<>(mapper.writeValueAsString(componentService.getComponentById(id_component)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получаем деталь с нужным uid и списком вложенных деталей.
     * @param id_component детали
     * @return деталь со списком компонентов
     */
    @RequestMapping(value = "/assamble/{id_component}")
    public ResponseEntity<Component> getComponentByIdWithAssamble(@PathVariable int id_component){
        return new ResponseEntity<>(componentService.getComponentByIdWithAssamble(id_component), HttpStatus.OK);
    }


    /**
     * Обновление информации детали по uid
     * ссылка вида: http://localhost:8080/api/component/update?id=2&name=Gaika2
     * @param id детали
     * @param name название детали
     * @return статус HTTP на изменение детали
     */
    @RequestMapping(value = "/update", params = {"id", "name"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateComponentById(@RequestParam int id, @RequestParam String name){
        try{
            componentService.updateComponentById(id, name);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Добавляем новую деталь
     * ссылка вида http://localhost:8080/api/component/add?name=Gaika
     * @param name наименование детали
     * @return статус HTTP запроса на добавление новой детали
     */
    @RequestMapping(value = "/add", params = {"name"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addNewComponent(@RequestParam String name){
        try{
            componentService.addNewComponent(name);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Добавляем ссылку на дочерний компонент, и делаем связку в таблице Assamble
     * @param id детали к которой хотим прикрепить дочернюю деталь
     * @param link_component ссылка на компонент
     * @return статус HTTP запроса на добавление дочерней детали
     */
    @Transactional
    @RequestMapping(value = "/insert",
            params = {"id", "link_component"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> addAssambleToComponent(@RequestParam int id,
                                                       @RequestParam int link_component){
        try{
            /*
            Проверяем существует ли индекс для дочерних деталей, если сущевствует то добавляем uid детали,
            если отсутствует присваиваем новый индекс и добавляем.
             */
            if(componentService.getComponentById(id).getId_assamble() != 0){
                assambleService.addAssamble(link_component, componentService.getComponentById(id).getId_assamble());
            } else {
                int index = assambleService.getIndexAssamble(); //получаем свободный индекс
                componentService.updateAssambleComponent(id, index); //добавляем ссылку на дочерние детали
                assambleService.addAssamble(link_component, index); //добавляем ссылку непосредственно на деталь
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Удаление записи компонента по uid
     * @param id uid компонента
     * @return статус HTTP запроса на удаление компонента
     */
    @RequestMapping(value = "/delete", params = {"id"})
    public ResponseEntity<Void> deleteComponent(@RequestParam int id){
        try{
            if(!componentService.getComponentById(id).isEnding()){
                componentService.deleteComponent(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
