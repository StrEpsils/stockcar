package com.cherkasov.stockcar.mapper;

import com.cherkasov.stockcar.entity.Feature;
import com.cherkasov.stockcar.entity.FeatureValue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FeatureValueMapper {

    /**
     * Получаем все существующие характеристики
     * @return список всех характеристик
     */
    @Select("Select * from feature")
    public List<Feature> selectAllFeature();

    /**
     * Получаем характеристики по uid детали
     * @param id_component
     * @return список характеристик одной детали
     */
    @Select("Select * from feature_value where id_component = #{id_component}")
    @Results(
            @Result(property = "feature", column = "id_feature", many = @Many(select = "selectFeatureById"))
    )
    public List<FeatureValue> selectFeatureByComponent(int id_component);

    /**
     * Выбираем характеристику по uid
     * @param id_feature uid характеристики
     * @return характеристика
     */
    @Select("Select * from feature where id_feature = #{id_feature}")
    public Feature selectFeatureById(int id_feature);

    /**
     * Добавляем новую характеристику
     * @param name наименование
     */
    @Insert("Insert into feature(name) values (#{name})")
    void addNewFeature(@Param("name") String name);

    /**
     * Удаление записи об характеристики
     * @param id_feature uid характеристики
     */
    @Delete("Delete from feature where id_feature = #{id_feature}")
    void deleteFeature(@Param("id_feature") int id_feature);

    /**
     * Добавление характеристики детали
     * @param id_feature uid характеристики
     * @param id_component uid компонента
     * @param value значение
     */
    @Insert("Insert into feature_value(value, id_feature, id_component) values (#{value}, #{id_feature}, #{id_component})")
    void addFeatureComponent(@Param("id_feature") int id_feature, @Param("id_component") int id_component, @Param("value") String value);
}
