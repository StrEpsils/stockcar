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

    /**
     * @return список всех автомобилей
     */
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
     */
    public void updateComponentById(int id_component, String name){
        componentMapper.updateComponentById(id_component, name);
    }

    /**
     * Добавление новой детали
     * @param name
     */
    public void addNewComponent(String name){
        componentMapper.addNewComponentWithoutId(name);
    }

    /**
     * Обновляем ссылку на дочерние детали
     * @param id_component деталь которой надо присвоить индекс
     * @param link_component ссылка на деталь которая должна быть дочерней
     */
    public void updateAssambleComponent(int id_component, int link_component){
         componentMapper.updateAssambleToComponent(id_component, link_component);
    }

    /**
     * Получаем автомобиль со списком деталей
     * @param id_auto uid автомобиля
     * @return автомобиль со списком дочерних деталей
     */
    public Component getAutoWithComponents(int id_auto) {
        return componentMapper.selectAutoWithComponents(id_auto);
    }

    /**
     * Добавление нового автомобиля
     * @param name наименование автомобиля
     */
    public void addNewAuto(String name) {
        componentMapper.addNewAuto(name);
    }

    /**
     * Удаляем компонент или авто
     * @param id_auto uid компонта - автомобиля
     */
    public void deleteComponent(int id_auto) {
        componentMapper.deleteComponentById(id_auto);
    }

    public int getLastInsertComponent() {
        return componentMapper.lastIndex();
    }
}
