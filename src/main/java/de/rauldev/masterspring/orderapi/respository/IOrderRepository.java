package de.rauldev.masterspring.orderapi.respository;

import de.rauldev.masterspring.orderapi.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends CrudRepository<OrderEntity,Long> { }
