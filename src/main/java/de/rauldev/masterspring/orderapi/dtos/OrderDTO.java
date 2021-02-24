package de.rauldev.masterspring.orderapi.dtos;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private List<OrderLineDTO> lines;
    private String createdAt;
    private Double total;
    private UserDTO user;
}
