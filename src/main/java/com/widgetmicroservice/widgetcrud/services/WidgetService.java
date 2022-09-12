package com.widgetmicroservice.widgetcrud.services;

import com.widgetmicroservice.widgetcrud.exceptions.NoWidgetsFound;
import com.widgetmicroservice.widgetcrud.exceptions.WidgetNotFound;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.models.WidgetReqBody;
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
        List<Widget> allWidgets = widgetRepo.findAll();
        if (allWidgets.size() == 0){
            throw new NoWidgetsFound("no widgets in database");
        } else {
            return allWidgets;
        }
    }

    public Optional<Widget> getWidgetById(Long id) {
        Optional<Widget> widgetById = widgetRepo.findById(id);
        if (widgetById.isPresent()) {
            return widgetById;
        } else {
            throw new WidgetNotFound("widget with id " + id + " not found");
        }
    }

    public void addWidget(WidgetReqBody widgetReqBody) {
        widgetRepo.save(
                new Widget(
                        widgetReqBody.getFirstName(),
                        widgetReqBody.getLastName(),
                        widgetReqBody.getAge(),
                        widgetReqBody.getGender(),
                        widgetReqBody.getHeight(),
                        widgetReqBody.getWeight())
        );
    }

    public void deleteWidgetById(Long id) {
        Optional<Widget> widgetById = widgetRepo.findById(id);
        if (widgetById.isPresent()) {
            widgetRepo.deleteById(id);
        } else {
            throw new WidgetNotFound("widget with id " + id + " not found");
        }
    }

    public void updateWidgetById(Long id, WidgetReqBody widgetReqBody) {
        Optional<Widget> widgetById = widgetRepo.findById(id);
        if (widgetById.isPresent()){
            Widget updateWidget = widgetById.get();
            updateWidget.setFirstName(widgetReqBody.getFirstName());
            updateWidget.setLastName(widgetReqBody.getLastName());
            updateWidget.setAge(widgetReqBody.getAge());
            updateWidget.setGender(widgetReqBody.getGender());
            updateWidget.setHeight(widgetReqBody.getHeight());
            updateWidget.setWeight(widgetReqBody.getWeight());
            widgetRepo.save(updateWidget);
        } else {
            throw new WidgetNotFound("widget with id " + id + " not found");
        }
    }
}
