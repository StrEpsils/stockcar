package com.cherkasov.stockcar.mapper;

import com.cherkasov.stockcar.entity.Assamble;
import com.cherkasov.stockcar.entity.Component;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@org.springframework.stereotype.Component
public interface ComponentMapper {

    /**
     * Заспросы передавамые в аннотации
     */
    String selectAllComponents = "Select * from component where ending = false";
    String selectComponentById = "Select * from component where id_component = #{id_component}";
    String selectFinalAuto = "Select * from component where ending = true";

    /**
     * @return list всех доступных деталей
     */
    @Select(selectAllComponents)
    public List<Component> selectAllComponent();

    /**
     * Получаем список готовых сборок автомобиля
     * @return
     */
    @Select(selectFinalAuto)
    public List<Component> selectAllAuto();

    /**
     * @param id_component uid детали
     * @return Component деталь
     */
    @Select(selectComponentById)
    @Results(value = {
            @Result(property = "id_component", column = "id_componnet"),
            @Result(property = "name", column = "name"),
            @Result(property = "id_assamble", column = "id_assamble")
    })
    public Component findById(int id_component);


    /**
     * @param id_component uid детали
     * @return деталь
     */
    @Select(selectComponentById)
    @Results(value = {
            @Result(property = "assamble", column = "id_assamble",
                    many = @Many(select = "com.cherkasov.stockcar.mapper.AssambleMapper.selectAssambleByIdWhithComponent"))
    })
    public Component selectComponentByIdWithAssamble(int id_component);

    /**
     * Обновление записи детали по uid
     * @param id_component детали
     * @param name название детали
     * @return деталь
     */
    @Update("Update component set name = #{name} where id_component = #{id_component}")
    public Component updateComponentById(@Param("id_component") int id_component,@Param("name") String name);

    /**
     * Добавление записи по новой детали
     * @param name наименование
     * @return новая запись
     */
    @Insert("Insert into component(name) values (#{name})")
    public Component addNewComponentWithoutId(@Param("name") String name);

    /**
     * Добавляем ссылку на дочернюю деталь
     * @param id_component компонента
     * @param id_assamble дочерней детали
     */
    @Update("Update component set id_assamble = #{id_assamble} where id_component = #{id_component}")
    public void updateAssambleToComponent(@Param("id_component") int id_component, @Param("id_assamble") int id_assamble);

}
