package com.cherkasov.stockcar.service;

import com.cherkasov.stockcar.entity.Component;
import com.cherkasov.stockcar.mapper.ComponentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "componentService")
public class ComponentService {

    @Autowired
    ComponentMapper componentMapper;

    /**
     * @return список всех деталей
     */
    public List<Component> getAllComponent(){
        return componentMapper.selectAllComponent();
    }

    public List<Component> getAllAuto(){
        return componentMapper.selectAllAuto();
    }

    /**
     * @param id_component uid детали
     * @return деталь
     */
    public Component getComponentById(int id_component){
        return componentMapper.findById(id_component);
    }

    /**
     * Возвращаем деталь со вложенными компонентами
     * @param id_component uid детали
     * @return деталь с состовными деталями по uid
     */
    public Component getComponentByIdWithAssamble(int id_component){
        return componentMapper.selectComponentByIdWithAssamble(id_component);
    }

    /**
     * Обновление детали по uid
     * @param id_component детали
     * @param name название
     * @return измененая деталь
     */
    public Component updateComponentById(int id_component, String name){
        return componentMapper.updateComponentById(id_component, name);
    }

    /**
     * Добавление новой детали
     * @param name
     * @return
     */
    public Component addNewComponent(String name){
        return componentMapper.addNewComponentWithoutId(name);
    }

    public void updateAssambleComponent(int id_component, int link_component){
         componentMapper.updateAssambleToComponent(id_component, link_component);
    }
}
