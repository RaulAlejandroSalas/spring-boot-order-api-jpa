package de.rauldev.masterspring.orderapi.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class ProductDTO {
	private Long id;
	private String name;
	private Double price;
}
