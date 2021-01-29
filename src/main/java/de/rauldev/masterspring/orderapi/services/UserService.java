package de.rauldev.masterspring.orderapi.services;

import de.rauldev.masterspring.orderapi.entities.UserEntity;
import de.rauldev.masterspring.orderapi.exceptions.GeneralServiceException;
import de.rauldev.masterspring.orderapi.exceptions.NotDataFoundException;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;
import de.rauldev.masterspring.orderapi.repository.IUserRepository;
import de.rauldev.masterspring.orderapi.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService
{
    @Autowired
    private IUserRepository userRepository;

    public UserEntity createUser(UserEntity userEntity){
        try {
            UserValidator.validate(userEntity);
            log.debug("createUser => ");
            //check if exist a username
            UserEntity existUser = userRepository.findByUsername(userEntity.getUsername()).orElse(null);
            if(existUser!=null){
                throw new ValidateServiceException("The username exists");
            }
            return userRepository.save(userEntity);
        } catch (ValidateServiceException | NotDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
