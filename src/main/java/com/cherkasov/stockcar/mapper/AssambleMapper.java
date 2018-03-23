package com.cherkasov.stockcar.mapper;

import com.cherkasov.stockcar.entity.Assamble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface AssambleMapper {

    String selectAllAssamble = "Select * from assamble";
    String selectAssambleById = "Select * from assamble where id_assamble = #{id_assamble}";

    @Select(selectAllAssamble)
    public List<Assamble> selectAllAssamble();

    @Select(selectAssambleById)
    public Assamble selectAllAssambleById(int id_assamble);


    /**
     * Выбор дочерних деталей по uid
     * @param id_assamble ссылка на детали
     * @return список дочерних деталей
     */
    @Select(selectAssambleById)
    @Results(value = @Result(property = "component", column = "id_component",
            many = @Many(select = "com.cherkasov.stockcar.mapper.ComponentMapper.findById")))
    public List<Assamble> selectAssambleByIdWhithComponent(int id_assamble);

    /**
     * Получение последнего свободного индекса
     * @return индекс
     */
    @Select("Select max(id_assamble)+1 from assamble")
    public Integer getIndex();

    /**
     * Добавление к записи детали ссылки на список дочерних деталей
     * @param id_component uid компонента которому присваиваем ссылку
     * @param id_assamble ссылка (генерируется сама)
     */
    @Insert("Insert into assamble(id_component, id_assamble) value (#{id_component}, #{id_assamble})")
    public void addAssambleToComponent(@Param("id_component") int id_component, @Param("id_assamble") int id_assamble);
}
