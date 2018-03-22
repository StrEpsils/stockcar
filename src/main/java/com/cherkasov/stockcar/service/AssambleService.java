package com.cherkasov.stockcar.service;

import com.cherkasov.stockcar.entity.Assamble;
import com.cherkasov.stockcar.mapper.AssambleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "assambleService")
public class AssambleService {

    @Autowired
    AssambleMapper assambleMapper;

    /**
     *  Получаем все составные детали
     * @param id_assamble uid коставной детали
     * @return все составные детали
     */
    public List<Assamble> getAssambleWithComponent(int id_assamble){
        return assambleMapper.selectAssambleByIdWhithComponent(id_assamble);
    }

    /**
     * Получаем последний свободный индекс
     * @return индекс
     */
    public Integer getIndexAssamble(){
        return assambleMapper.getIndex();
    }

    /**
     * Добавляем к индексу указание на деталь,
     * которая является частью сборки другой детали
     * @param link_component uid детали
     * @param index индекс указанный в родительской детали
     */
    public void addAssamble(int link_component, int index) {
         assambleMapper.addAssambleToComponent(link_component, index);
    }
}
