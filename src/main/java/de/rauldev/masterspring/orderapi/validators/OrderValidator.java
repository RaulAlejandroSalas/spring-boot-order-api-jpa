package de.rauldev.masterspring.orderapi.validators;

import de.rauldev.masterspring.orderapi.entities.OrderEntity;
import de.rauldev.masterspring.orderapi.entities.OrderLineEntity;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;

public class OrderValidator {

    private OrderValidator(){}
    public static void validate(OrderEntity order){
        if(order.getLines().isEmpty() || order.getLines() == null){
            throw new ValidateServiceException("The Lines is required");
        }
        //Lines validator
        for (OrderLineEntity orderLineEntity:
             order.getLines()) {
            if(orderLineEntity.getPrice()==null){
                throw new ValidateServiceException("The Price is required");
            }
            if(orderLineEntity.getPrice() <0){
                throw new ValidateServiceException("The Price must >0");
            }
            if(orderLineEntity.getProduct() == null || orderLineEntity.getProduct().getId()==null){
                throw new ValidateServiceException("The Product is required");
            }
            if(orderLineEntity.getQuantity() <0){
                throw new ValidateServiceException("The Quantity must >0");
            }
        }
    }
}
