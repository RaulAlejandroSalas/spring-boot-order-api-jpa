package de.rauldev.masterspring.orderapi.validators;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;

public class ProductValidator {

	public static void validate(ProductEntity productEntity) {
		if(productEntity.getName()==null || productEntity.getName().trim().isEmpty())
		{
			throw new RuntimeException("The name is required");
		}
		if(productEntity.getName().length()>100) {
			throw new RuntimeException("The name must < 100 characters");
		}
		if(productEntity.getPrice()==null) {
			throw new RuntimeException("The price is required");
		}
		if(productEntity.getPrice()<0) {
			throw new RuntimeException("The price must > 0");
		}
	}
}
