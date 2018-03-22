package com.cherkasov.stockcar.mapper;

import com.cherkasov.stockcar.entity.Component;
import com.cherkasov.stockcar.entity.Stock;
import com.cherkasov.stockcar.entity.StockComponent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StockComponentMapper {

    /**
     * Список запросов
     */
    String selectAllComponentsByStock = "Select * from component_has_stock where id_stock = #{id_stock}";
    String selectComponentById = "Select * from component where id_component = #{id_component}";
    String selectAllStock = "Select * from stock";

    /**
     * Список компонентов в рамках одного склада
     * @param id_stock uid склада
     * @return список компонентов и их количество на складе
     */
    @Select(selectAllComponentsByStock)
    @Results(value = {
            @Result(property = "component", column = "id_component", many = @Many(select = "selectComponents"))
    })
    public List<StockComponent> selectAllComponentStock (int id_stock);

    /**
     * Получаем компонент по uid
     * @param id_component
     * @return компонент
     */
    @Select(selectComponentById)
    public Component selectComponents(int id_component);

    @Select(selectAllStock)
    public List<Stock> selectAllStock();



}
