package de.rauldev.masterspring.orderapi.repository;

import de.rauldev.masterspring.orderapi.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
}
