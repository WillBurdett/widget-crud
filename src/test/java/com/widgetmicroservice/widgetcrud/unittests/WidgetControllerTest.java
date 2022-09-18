package com.widgetmicroservice.widgetcrud.unittests;

import com.widgetmicroservice.widgetcrud.controllers.WidgetController;
import com.widgetmicroservice.widgetcrud.enums.Gender;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.models.WidgetReqBody;
import com.widgetmicroservice.widgetcrud.services.WidgetService;
import com.widgetmicroservice.widgetcrud.utils.JsonUtil;
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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WidgetController.class)
public class WidgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetService widgetService;

    @Test
    public void getAllWidgets_HappyPath() throws Exception {
        // given
        Widget expected = new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        when(widgetService.getAllWidgets()).thenReturn(List.of(expected));

        // when
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
        // then
        verify(widgetService, times(1)).getAllWidgets();
    }

    @Test
    public void getWidgetById_HappyPath() throws Exception {
        // given
        Widget expected = new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        when(widgetService.getWidgetById(1L)).thenReturn(Optional.of(expected));

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/widgets/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Bob")))
                .andExpect(jsonPath("$.lastName", is("Smith")))
                .andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.height", is(150.0)))
                .andExpect(jsonPath("$.weight", is(80.0)));
        // then
        verify(widgetService, times(1)).getWidgetById(1L);
    }

    @Test
    public void addWidget_HappyPath() throws Exception {
        // given
        WidgetReqBody expected = new WidgetReqBody("Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        // when
        mockMvc.perform(post("/widgets").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(expected)));
        // then
        verify(widgetService, times(1)).addWidget(expected);
    }

    @Test
    public void deleteWidgetById_HappyPath() throws Exception {
        // when
        mockMvc.perform(delete("/widgets/1"));
        // then
        verify(widgetService, times(1)).deleteWidgetById(1L);
    }

    @Test
    public void updateWidget_HappyPath() throws Exception {
        // given
        WidgetReqBody expected = new WidgetReqBody("Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        // when
        mockMvc.perform(put("/widgets/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(expected)));
        // then
        verify(widgetService, times(1)).updateWidgetById(1L, expected);
    }
}