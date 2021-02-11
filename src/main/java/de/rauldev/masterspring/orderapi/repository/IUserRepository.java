package de.rauldev.masterspring.orderapi.repository;

import de.rauldev.masterspring.orderapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByusername(String username);
}
