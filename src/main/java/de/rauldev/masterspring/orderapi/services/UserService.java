package de.rauldev.masterspring.orderapi.services;

import de.rauldev.masterspring.orderapi.consts.ApplicationConst;
import de.rauldev.masterspring.orderapi.converters.UserConverter;
import de.rauldev.masterspring.orderapi.dtos.LoginRequestDTO;
import de.rauldev.masterspring.orderapi.dtos.LoginResponseDTO;
import de.rauldev.masterspring.orderapi.entities.UserEntity;
import de.rauldev.masterspring.orderapi.exceptions.GeneralServiceException;
import de.rauldev.masterspring.orderapi.exceptions.NotDataFoundException;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;
import de.rauldev.masterspring.orderapi.repository.IUserRepository;
import de.rauldev.masterspring.orderapi.validators.UserValidator;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
public class UserService
{
    @Value("${jwt.password}")
    private String jwtSecret;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    public UserEntity createUser(UserEntity userEntity){
        try {
            UserValidator.validate(userEntity);
            log.debug("createUser => " + userEntity.toString());
            //check if exist a username
            UserEntity existUser = userRepository.findByUsername(userEntity.getUsername())
                                                 .orElse(null);
            if(existUser!=null){
                throw new ValidateServiceException(ApplicationConst.USER_EXIST);
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

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        try {
            log.debug("login => ");
            UserEntity user = userRepository.findByUsername(loginRequestDTO.getUsername())
                                      .orElseThrow(()->new ValidateServiceException(ApplicationConst.ERROR_USER_PASSWORD));
            if(!user.getPassword().equals(loginRequestDTO.getPassword())) throw new ValidateServiceException(ApplicationConst.ERROR_USER_PASSWORD);
            String token = createToken(user);
            return LoginResponseDTO.builder()
                                   .user(userConverter.fromEntity(user))
                                   .token(token)
                                   .build();
        } catch (ValidateServiceException | NotDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public String createToken(UserEntity userEntity){
        Date now = new Date();
        Date expiryDay = new Date(now.getTime() + (1000*60*60)); //1000*60*60 (1 Hour)

        return Jwts.builder()
                    .setSubject(userEntity.getUsername())
                    .setIssuedAt(now)
                    .setExpiration(expiryDay)
                    .signWith(SignatureAlgorithm.HS256,jwtSecret)
                    .compact();

    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException e){
            log.error("Jwt is a particular format/config that does not match the format");

        }catch (MalformedJwtException e){
            log.error("JWT was not correctly constructed and should be rejected");
        }
        catch (SignatureException e){
            log.error("Signature or verifying an existing signature of a JWT failed");
        }
        catch (ExpiredJwtException e){
            log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }

}
