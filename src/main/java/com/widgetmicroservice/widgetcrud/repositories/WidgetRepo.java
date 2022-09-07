package com.widgetmicroservice.widgetcrud.repositories;

import com.widgetmicroservice.widgetcrud.models.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepo extends JpaRepository<Widget, Long> {
}
