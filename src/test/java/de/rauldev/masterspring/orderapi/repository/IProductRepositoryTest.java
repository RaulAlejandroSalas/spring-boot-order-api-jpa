package de.rauldev.masterspring.orderapi.repository;

import de.rauldev.masterspring.orderapi.BaseTest;
import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class IProductRepositoryTest extends BaseTest {


    @Autowired
    private IProductRepository productRepository;
  
    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Save a New Product")
    public void shouldSaveProduct(){
        ProductEntity product =  ProductEntity.builder()
                                             .name("Product Test Name")
                                             .price(200.0)
                                             .build();
        ProductEntity productSaved = productRepository.save(product);

        // Assert the response
        Assertions.assertThat(productSaved)
                  .usingRecursiveComparison()
                  .ignoringFields("id_product")
                  .isEqualTo(product);
    }

    @Test
    @DisplayName("Should Save a New Product From SQL File")
    @Sql("classpath:test-data.sql")
    public void  shouldSaveProductsThroughSqlFile(){
        Optional<ProductEntity> productEntity = productRepository.findById(1L);

        // Assert the response
        Assertions.assertThat(productEntity).isNotEmpty();
    }

    @Test
    @DisplayName("Should Delete a Product")
    public void shouldDeleteProduct(){
        ProductEntity product =  ProductEntity.builder()
                                              .name("Product Test Name")
                                              .price(200.0)
                                              .build();
        productRepository.save(product);
        productRepository.delete(product);
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should find all Product")
    public void findAllProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        Assertions.assertThat(productEntityList.size()).isEqualTo(24);
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Delete Product by Id")
    public void deleteProductById(){
        ProductEntity product =  ProductEntity.builder()
                                                .name("Product Test Name")
                                                .price(200.0)
                                                .build();
        ProductEntity productSaved = productRepository.save(product);
        productRepository.deleteById(productSaved.getId());
    }
}