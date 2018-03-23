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

    /**
     * Получение списка деталей на сладе и их количество
     * @param id_stock
     * @return список деталей с их количеством
     */
    public List<StockComponent> getAllComponentStock(int id_stock){
        return stockComponentMapper.selectAllComponentStock(id_stock);
    }

    public StockComponent getStockById(int id_stock){
        return stockComponentMapper.getStockById(id_stock);
    }

    /**
     * Добавление нового склада
     * @param name наименование склада
     */
    public void addNewStock(String name) {
        stockComponentMapper.createNewStock(name);
    }

    /**
     * Добавляем на слад деталь
     * @param index индекс последней добавленной детали
     * @param stock индекс склада
     * @param count количество деталей на складе
     */
    public void addComponentStock(int index, int stock, int count) {
        stockComponentMapper.addNewComponentInStock(index, stock, count);
    }

    /**
     * Редактируем количество деталей
     * @param id_component uid компонента
     * @param count количество деталей
     */
    public void editCount(int id_component, int count) {
        stockComponentMapper.editCountComponent(id_component, count);
    }
}
