package de.rauldev.masterspring.orderapi.validators;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;

public class ProductValidator {

	private ProductValidator(){}

	public static void validate(ProductEntity productEntity) {
		if(productEntity.getName()==null || productEntity.getName().trim().isEmpty())
		{
			throw new ValidateServiceException("The name is required");
		}
		if(productEntity.getName().length()>100) {
			throw new ValidateServiceException("The name must < 100 characters");
		}
		if(productEntity.getPrice()==null) {
			throw new ValidateServiceException("The price is required");
		}
		if(productEntity.getPrice()<0) {
			throw new ValidateServiceException("The price must > 0");
		}
	}
}
