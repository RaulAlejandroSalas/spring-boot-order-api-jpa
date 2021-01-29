package de.rauldev.masterspring.orderapi.repository;

import de.rauldev.masterspring.orderapi.entities.OrderLineEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderLineRepository extends CrudRepository<OrderLineEntity,Long> { }
