package com.cherkasov.stockcar.service;

import com.cherkasov.stockcar.entity.StockComponent;
import com.cherkasov.stockcar.mapper.StockComponentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "stockComponentService")
public class StockComponentService {

    @Autowired
    StockComponentMapper stockComponentMapper;

    public List<StockComponent> getAllComponentStock(int id_stock){
        return stockComponentMapper.selectAllComponentStock(id_stock);
    }

}
