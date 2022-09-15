package com.widgetmicroservice.widgetcrud.unittests;

import com.widgetmicroservice.widgetcrud.controllers.WidgetController;
import com.widgetmicroservice.widgetcrud.enums.Gender;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.services.WidgetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WidgetController.class)
public class WidgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetService widgetService;

    @Test
    public void getAllWidgets() throws Exception {
        // given
        Widget expected = new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        when(widgetService.getAllWidgets()).thenReturn(List.of(expected));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/widgets").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Bob")))
                .andExpect(jsonPath("$[0].lastName", is("Smith")))
                .andExpect(jsonPath("$[0].age", is(20)))
                .andExpect(jsonPath("$[0].gender", is("MALE")))
                .andExpect(jsonPath("$[0].height", is(150.0)))
                .andExpect(jsonPath("$[0].weight", is(80.0)));

        verify(widgetService, times(1)).getAllWidgets();
    }

    @Test
    public void getWidgetById() {
    }

    @Test
    public void addWidget() {
    }

    @Test
    public void deleteWidgetById() {
    }

    @Test
    public void updateWidget() {
    }
}