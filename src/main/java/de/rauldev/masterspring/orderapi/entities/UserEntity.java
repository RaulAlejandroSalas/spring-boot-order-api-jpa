package de.rauldev.masterspring.orderapi.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",length = 30,nullable = false)
    private String username;
    @Column(name = "password", nullable = false, length = 150)
    private String password;

}
