package de.rauldev.masterspring.orderapi.dtos;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDTO {
    private Long id;
    private ProductDTO product;
    private Double price;
    private Double quantity;
    private Double total;
}
