package com.cherkasov.stockcar.mapper;

import com.cherkasov.stockcar.entity.Feature;
import com.cherkasov.stockcar.entity.FeatureValue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FeatureValueMapper {

    String selectAllFeatureValue = "Select * from feature_value";
    String selectFeatureValueByIdComponent = "Select * from feature_value where id_component = #{id_component}";

    @Select(selectAllFeatureValue)
    public List<FeatureValue> selectAllFeatureValue();

    /**
     * Получаем характеристики по uid детали
     * @param id_component
     * @return
     */
    @Select(selectFeatureValueByIdComponent)
    @Results(
            @Result(property = "feature", column = "id_feature", many = @Many(select = "selectFeatureById"))
    )
    public List<FeatureValue> selectFeatureByComponent(int id_component);


    @Select("Select * from feature where id_feature = #{id_feature}")
    public Feature selectFeatureById(int id_feature);

}
