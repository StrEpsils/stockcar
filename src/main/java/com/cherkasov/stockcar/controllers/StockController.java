package com.cherkasov.stockcar.controllers;

import com.cherkasov.stockcar.entity.StockComponent;
import com.cherkasov.stockcar.service.StockComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    StockComponentService stockComponentService;

    @RequestMapping("/{id_stock}")
    public List<StockComponent> getComponentsByIdStock(@PathVariable int id_stock){
        return stockComponentService.getAllComponentStock(id_stock);
    }

}
