package com.widgetmicroservice.widgetcrud.repositories;

import com.widgetmicroservice.widgetcrud.models.Widget;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "widgets", path = "widgets")
public interface WidgetRepo extends PagingAndSortingRepository<Widget, Long> {
}
