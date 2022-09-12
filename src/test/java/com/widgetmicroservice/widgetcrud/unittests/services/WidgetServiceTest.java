package com.widgetmicroservice.widgetcrud.unittests.services;

import com.widgetmicroservice.widgetcrud.enums.Gender;
import com.widgetmicroservice.widgetcrud.exceptions.NoWidgetsFound;
import com.widgetmicroservice.widgetcrud.exceptions.WidgetNotFound;
import com.widgetmicroservice.widgetcrud.models.Widget;
import com.widgetmicroservice.widgetcrud.models.WidgetReqBody;
import com.widgetmicroservice.widgetcrud.repositories.WidgetRepo;
import com.widgetmicroservice.widgetcrud.services.WidgetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

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
        verify(widgetRepo, times(1)).findAll();
    }
    @Test
    public void getAllWidgets_ThrowsExceptionIfNoWidgetsOnRecord() {
        // given
        List emptyWidgetList = List.of();
        when(widgetRepo.findAll()).thenReturn(emptyWidgetList);
        // when
        assertThatThrownBy(() -> {
            undertest.getAllWidgets();
            // then
        }).isInstanceOf(NoWidgetsFound.class)
                .hasMessage("no widgets in database");
    }

    @Test
    public void getWidgetById_HappyPath() {
        // given
        Widget expected = new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        when(widgetRepo.findById(expected.getId())).thenReturn(Optional.of(expected));
        // when
        Optional<Widget> actual = undertest.getWidgetById(expected.getId());
        // then
        assertThat(actual).isEqualTo(Optional.of(expected));
        verify(widgetRepo, times(1)).findById(expected.getId());
    }

    @Test
    public void getWidgetById_ThrowsExceptionWhenWidgetNotFound() {
        // given
        Optional <Widget> expected = Optional.empty();
        when(widgetRepo.findById(1L)).thenReturn(expected);
        // when
        assertThatThrownBy(() -> {
            undertest.getWidgetById(1L);
            // then
        }).isInstanceOf(WidgetNotFound.class)
                .hasMessage("widget with id 1 not found");
        verify(widgetRepo, times(1)).findById(1L);
    }

    @Test
    public void addWidget_HappyPath() {
        // given
        WidgetReqBody widgetReqBody = new WidgetReqBody("Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        // when
        undertest.addWidget(widgetReqBody);
        // then
        verify(widgetRepo, times(1)).
                save(new Widget(
                        null,
                        widgetReqBody.getFirstName(),
                        widgetReqBody.getLastName(),
                        widgetReqBody.getAge(),
                        widgetReqBody.getGender(),
                        widgetReqBody.getHeight(),
                        widgetReqBody.getWeight()));
    }

    @Test
    public void deleteWidgetById_HappyPath() {
        // given
        Widget widget = new Widget(1L,"Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        when(widgetRepo.findById(widget.getId())).thenReturn(Optional.of(widget));
        // when
        undertest.deleteWidgetById(1L);
        // then
        verify(widgetRepo, times(1)).deleteById(1L);
    }

    @Test
    public void deleteWidgetById_ThrowsExceptionWhenWidgetNotFound() {
        // given
        Optional <Widget> expected = Optional.empty();
        when(widgetRepo.findById(1L)).thenReturn(expected);
        // when
        assertThatThrownBy(() -> {
            undertest.deleteWidgetById(1L);
            // then
        }).isInstanceOf(WidgetNotFound.class)
                .hasMessage("widget with id 1 not found");
        verify(widgetRepo, times(1)).findById(1L);
    }

    @Test
    public void updateWidgetById() {
        // given
        Widget old = new Widget(1L, "Bob", "Smith", 20, Gender.MALE, 150.0, 80.0);
        when(widgetRepo.findById(old.getId())).thenReturn(Optional.of(old));

        // when
        WidgetReqBody update = new WidgetReqBody("Sally", "Smith", 20, Gender.FEMALE, 150.0, 80.0);
        undertest.updateWidgetById(old.getId(), update);
        // then
        verify(widgetRepo, times(1)).save(new Widget(
                old.getId(),
                update.getFirstName(),
                update.getLastName(),
                update.getAge(),
                update.getGender(),
                update.getHeight(),
                update.getWeight()));
    }
}