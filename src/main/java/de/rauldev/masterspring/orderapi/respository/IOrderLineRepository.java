package de.rauldev.masterspring.orderapi.respository;

import de.rauldev.masterspring.orderapi.entities.OrderLineEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderLineRepository extends CrudRepository<OrderLineEntity,Long> { }
