package de.rauldev.masterspring.orderapi.converters;

import de.rauldev.masterspring.orderapi.dtos.SignUpRequestDTO;
import de.rauldev.masterspring.orderapi.dtos.UserDTO;
import de.rauldev.masterspring.orderapi.entities.UserEntity;

public class UserConverter implements IConverter<UserEntity, UserDTO>{

    @Override
    public UserDTO fromEntity(UserEntity entity) {
    	if(entity==null)return null;
        return UserDTO.builder().id(entity.getId())
                                .username(entity.getUsername())
                                .build();
    }

    @Override
    public UserEntity fromDTO(UserDTO dto) {
    	if(dto==null)return null;
        return UserEntity.builder()
                        .id(dto.getId())
                        .username(dto.getUsername())
                        .password(null)
                        .build();
    }

    public UserEntity signUp(SignUpRequestDTO signUpRequestDTO){
        return UserEntity.builder()
                         .username(signUpRequestDTO.getUsername())
                         .password(signUpRequestDTO.getPassword())
                         .build();
    }
}
