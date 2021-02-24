package de.rauldev.masterspring.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

}
