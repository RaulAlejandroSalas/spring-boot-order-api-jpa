package de.rauldev.masterspring.orderapi.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private UserDTO user;
    private String token;
}
