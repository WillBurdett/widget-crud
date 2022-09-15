package com.widgetmicroservice.widgetcrud.controllers;

import com.widgetmicroservice.widgetcrud.exceptions.WidgetNotFound;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.models.WidgetReqBody;
import com.widgetmicroservice.widgetcrud.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "widgets")
public class WidgetController {

    private final WidgetService widgetService;

    @Autowired
    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    private List<Widget> getAllWidgets(){
        return widgetService.getAllWidgets();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Widget> getWidgetById(@PathVariable Long id) throws WidgetNotFound {
        return widgetService.getWidgetById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addWidget(@RequestBody WidgetReqBody widgetReqBody){
        widgetService.addWidget(widgetReqBody);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteWidgetById(@PathVariable Long id){
        widgetService.deleteWidgetById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateWidget(@PathVariable Long id, @RequestBody WidgetReqBody widgetReqBody) throws WidgetNotFound {
        widgetService.updateWidgetById(id, widgetReqBody);
    }
}
