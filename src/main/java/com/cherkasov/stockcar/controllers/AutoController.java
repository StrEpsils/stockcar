package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.Component;
import com.cherkasov.stockcar.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auto")
public class AutoController {

    @Autowired
    ComponentService componentService;

    /**
     * Получаем список автомобилей
     * @return список авто
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Component> getAllAuto(){
        return componentService.getAllAuto();
    }
}
