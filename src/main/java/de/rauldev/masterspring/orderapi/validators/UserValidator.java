package de.rauldev.masterspring.orderapi.validators;

import de.rauldev.masterspring.orderapi.entities.UserEntity;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;

public class UserValidator {

    public static void validate(UserEntity userEntity){
        if(userEntity.getUsername() ==null || userEntity.getUsername().trim().isEmpty()){
            throw new ValidateServiceException("The username is required");
        }
        if(userEntity.getUsername().length()>30){
            throw new ValidateServiceException("The length of username must < 30 characters");
        }
        if(userEntity.getPassword() ==null || userEntity.getPassword().trim().isEmpty()){
            throw new ValidateServiceException("The password is required");
        }
        if(userEntity.getPassword().length()>30){
            throw new ValidateServiceException("The length of password must < 30 characters");
        }

    }
}
