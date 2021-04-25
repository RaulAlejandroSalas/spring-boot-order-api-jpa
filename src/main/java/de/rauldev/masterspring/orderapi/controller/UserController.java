package de.rauldev.masterspring.orderapi.controller;

import de.rauldev.masterspring.orderapi.consts.ApplicationConst;
import de.rauldev.masterspring.orderapi.converters.UserConverter;
import de.rauldev.masterspring.orderapi.dtos.LoginRequestDTO;
import de.rauldev.masterspring.orderapi.dtos.LoginResponseDTO;
import de.rauldev.masterspring.orderapi.dtos.SignUpRequestDTO;
import de.rauldev.masterspring.orderapi.dtos.UserDTO;
import de.rauldev.masterspring.orderapi.entities.UserEntity;
import de.rauldev.masterspring.orderapi.services.UserService;
import de.rauldev.masterspring.orderapi.utils.WrapperResponse;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Timed("post-signup")
    @PostMapping(value = "/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signUp(@RequestBody SignUpRequestDTO signUpRequest) {
        UserEntity userEntity = userService.createUser(userConverter.signUp(signUpRequest));
        return new WrapperResponse<>(true, ApplicationConst.SUCCESS, userConverter.fromEntity(userEntity))
                .createResponse(HttpStatus.CREATED);
    }
    @Timed("post-login")
    @PostMapping(value = "/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = userService.login(loginRequestDTO);
        return new WrapperResponse<>(true, ApplicationConst.SUCCESS, loginResponseDTO).createResponse(HttpStatus.OK);
    }
}
