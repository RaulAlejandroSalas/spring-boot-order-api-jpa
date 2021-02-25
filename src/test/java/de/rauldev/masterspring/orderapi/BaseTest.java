package de.rauldev.masterspring.orderapi;

import org.testcontainers.containers.MySQLContainer;

public class BaseTest {
    static MySQLContainer<?> mySQLContainer;
    static {
        mySQLContainer = new MySQLContainer<>("mysql:latest")
                .withDatabaseName("ordersdb")
                .withUsername("raul")
                .withPassword("server2020")
                .withReuse(true);
        mySQLContainer.start();
    }
}
