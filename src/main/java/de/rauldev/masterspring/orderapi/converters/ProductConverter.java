package de.rauldev.masterspring.orderapi.converters;

import de.rauldev.masterspring.orderapi.dtos.ProductDTO;
import de.rauldev.masterspring.orderapi.entities.ProductEntity;

public class ProductConverter implements IConverter<ProductEntity,ProductDTO> {

	@Override
	public ProductDTO fromEntity(ProductEntity entity) {
		return ProductDTO.builder()
						 .id(entity.getId())
						 .name(entity.getName())
						 .price(entity.getPrice())
						 .build();
	}

	@Override
	public ProductEntity fromDTO(ProductDTO dto) {
		return ProductEntity.builder()
				 .id(dto.getId())
				 .name(dto.getName())
				 .price(dto.getPrice())
				 .build();
	}

}
