package de.rauldev.masterspring.orderapi.repository;

import de.rauldev.masterspring.orderapi.entities.OrderEntity;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class IOrderRepositoryTest {
    @Autowired
    private IOrderRepository orderRepository;

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldSaveOrder(){
        OrderEntity order = OrderEntity.builder()
                                       .lines(null)
                                       .user(null)
                                       .createdAt(LocalDateTime.now())
                                       .total(100.9)
                                       .build();
        OrderEntity orderEntitySaved = orderRepository.save(order);
        // Assert the response
        Assertions.assertThat(orderEntitySaved).usingRecursiveComparison().isEqualTo(orderEntitySaved);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void  shouldSaveOrdersThroughSqlFile(){
        Optional<OrderEntity> orderEntity = orderRepository.findById(1L);
        // Assert the response
        Assertions.assertThat(orderEntity).isNotEmpty();
    }
}