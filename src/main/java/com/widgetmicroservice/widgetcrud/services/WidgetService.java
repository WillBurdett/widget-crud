package com.widgetmicroservice.widgetcrud.services;

import com.widgetmicroservice.widgetcrud.exceptions.WidgetNotFound;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.repositories.WidgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WidgetService {

    private final WidgetRepo widgetRepo;

    @Autowired
    public WidgetService(WidgetRepo widgetRepo) {
        this.widgetRepo = widgetRepo;
    }

    public List<Widget> getAllWidgets() {
        return widgetRepo.findAll();
    }

    public Optional<Widget> getWidgetById(Long id) {
        return widgetRepo.findById(id);
    }

    public void addWidget(Widget widget) {
        widgetRepo.save(widget);
    }

    public void deleteWidgetById(Long id) {
        widgetRepo.deleteById(id);
    }

    public void updateWidgetById(Long id, Widget widget) {
        Optional<Widget> pokemonById = widgetRepo.findById(id);
        if (pokemonById.isPresent()){
            Widget updateWidget = pokemonById.get();
            updateWidget.setFirstName(widget.getFirstName());
            updateWidget.setLastName(widget.getLastName());
            updateWidget.setAge(widget.getAge());
            updateWidget.setGender(widget.getGender());
            updateWidget.setHeight(widget.getHeight());
            updateWidget.setWeight(widget.getWeight());
            widgetRepo.save(updateWidget);
        } else {
            throw new WidgetNotFound("widget with id " + id + " not found");
        }
    }
}
