package de.rauldev.masterspring.orderapi.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_lines")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_order", nullable = false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "fk_product",nullable = false)
    private ProductEntity product;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "total")
    private Double total;
}
