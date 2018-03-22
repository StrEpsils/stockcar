package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.Assamble;
import com.cherkasov.stockcar.service.AssambleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest контроллер составных деталей
 * @author scherkasov
 */

@RestController
@RequestMapping(value = "/api/assamble")
public class AssambleController {

    @Autowired
    AssambleService assambleService;

    /**
     * Получаем список составных деталей
     * @param id_assamble
     * @return список деталей
     */
    @RequestMapping(value = "/component/{id_assamble}")
    public ResponseEntity<List<Assamble>> getAssambleByIdWithComponent(@PathVariable int id_assamble){
        return new ResponseEntity<>(assambleService.getAssambleWithComponent(id_assamble), HttpStatus.OK);
    }

}
