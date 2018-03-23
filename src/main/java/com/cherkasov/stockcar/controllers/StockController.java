package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.StockComponent;
import com.cherkasov.stockcar.service.ComponentService;
import com.cherkasov.stockcar.service.StockComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    StockComponentService stockComponentService;
    @Autowired
    ComponentService componentService;

    /**
     * Получаем список компонентов на складе
     * @param id_stock uid склада
     * @return список компонентов
     */
    @RequestMapping(value = "/{id_stock}", method = RequestMethod.GET)
    public ResponseEntity<List<StockComponent>> getComponentsByIdStock(@PathVariable int id_stock){
        return new ResponseEntity<>(stockComponentService.getAllComponentStock(id_stock), HttpStatus.OK);
    }

    /**
     * Добавление нового склада
     * @param name наименование склада
     * @return статус HTTP запроса на добавление нового склада
     */
    @RequestMapping(value = "/add", params = {"name"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addNewStock(@RequestParam String name){
        stockComponentService.addNewStock(name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Добавляем деталь на склад с определенным uid
     * @param stock uid склада
     * @param count количество деталей
     * @param name наименование детали
     * @return статус HTTP запроса на добавление детали на склад
     */
    @Transactional
    @RequestMapping(value = "/addcomponent", params = {"stock","name", "count"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addComponentInStock(@RequestParam int stock,
                                                    @RequestParam int count, @RequestParam String name){
        try {
            //TODO: по уму добавить бы еще сюда проверку на существование данного слада
            componentService.addNewComponent(name); // добавляем деталь
            int index = componentService.getLastInsertComponent(); //получаем uid детали
            stockComponentService.addComponentStock(index, stock, count); //добавляем деталь на склад
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Редактируем количество деталей на складе
     * @param id uid детали
     * @param count количество деталей
     * @return ответ HTTP запроса на изменение количества деталей
     */
    @RequestMapping(value = "/editcount", params = {"id", "count"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> editCountComponent(@RequestParam int id, @RequestParam int count){
        try{
            stockComponentService.editCount(id,count);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
