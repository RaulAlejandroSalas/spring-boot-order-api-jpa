package de.rauldev.masterspring.orderapi.securiry;

import de.rauldev.masterspring.orderapi.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
/*
* User Authenticated Wrapper Class for Spring Boot boosting detection
* */

@AllArgsConstructor
@Getter
public class PrincipalUser implements UserDetails {

    private UserEntity userEntity;
    private Collection<? extends GrantedAuthority> authorities;

    public static PrincipalUser create(UserEntity userEntity){
        List<GrantedAuthority> grantedAuthorityList = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );
        return new PrincipalUser(userEntity,grantedAuthorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
