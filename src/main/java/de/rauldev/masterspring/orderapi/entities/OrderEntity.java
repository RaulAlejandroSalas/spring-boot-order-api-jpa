package de.rauldev.masterspring.orderapi.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderLineEntity> lines;
    @Column(name = "total",nullable = false)
    private Double total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        OrderEntity that = (OrderEntity) o;
        return getId().equals(that.getId()) && getLines().equals(that.getLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLines());
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", lines=" + lines +
                ", total=" + total +
                '}';
    }
}
