package com.widgetmicroservice.widgetcrud.unittests.services;

import com.widgetmicroservice.widgetcrud.enums.Gender;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.repositories.WidgetRepo;
import com.widgetmicroservice.widgetcrud.services.WidgetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(WidgetService.class)
public class WidgetServiceTest {


    @Autowired
    WidgetService undertest;

    @MockBean
    WidgetRepo widgetRepo;

    @Test
    public void getAllWidgets_HappyPath() {
        // given
        List expected = List.of(new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0));
        when(widgetRepo.findAll()).thenReturn(expected);
        // when
        List actual = undertest.getAllWidgets();
        // then
        assertThat(actual).isEqualTo(expected);
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
    public void updateWidgetById() {
    }
}