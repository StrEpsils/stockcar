package com.cherkasov.stockcar.mapper;

import com.cherkasov.stockcar.entity.Component;
import com.cherkasov.stockcar.entity.Stock;
import com.cherkasov.stockcar.entity.StockComponent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@org.springframework.stereotype.Component
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

    /**
     * Добавляем запись о новом складе
     * @param name наименование склада
     */
    @Insert("Insert into stock(name) values (#{name})")
    void createNewStock(@Param("name") String name);

    /**
     * Добавляем запись о детали и количестве
     * @param id_component uid компонента
     * @param id_stock uid склада на котором деталь находится
     * @param count количество деталей
     */
    @Insert("Insert into component_has_stock(id_component, id_stock, count) values (#{id_component}, #{id_stock}, #{count})")
    void addNewComponentInStock(@Param("id_component") int id_component, @Param("id_stock") int id_stock, @Param("count") int count);

    /**
     * Получаем склад по uid
     * @param id_stock uid склада
     * @return склад
     */
    @Select("Select * from stock where id_stock = #{id_stock}")
    StockComponent getStockById(int id_stock);

    /**
     * Обновляем запись о количестве определенной детали
     * @param id_component uid детали
     * @param count количество деталей
     */
    @Update("Update component_has_stock set count = #{count} where id_component = #{id_component}")
    void editCountComponent(@Param("id_component") int id_component, @Param("count") int count);
}
