/**
 * 
 */
package de.rauldev.masterspring.orderapi.securiry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import de.rauldev.masterspring.orderapi.consts.ApplicationConst;
import de.rauldev.masterspring.orderapi.entities.UserEntity;
import de.rauldev.masterspring.orderapi.exceptions.NotDataFoundException;
import de.rauldev.masterspring.orderapi.repository.IUserRepository;
import de.rauldev.masterspring.orderapi.services.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author raul
 *
 */
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			//Extract token from Request
			String jwt = getJwtResponse(request);
		
			if(StringUtils.hasText(jwt) && userService.validateToken(jwt)) {
		
				//Extract userName from token
				String username = userService.getUserNameFromToken(jwt);
				
				UserEntity userEntity = userRepository.findByusername(username)
													  .orElseThrow(()->new NotDataFoundException(ApplicationConst.NOT_USER_FOUND));	
				
				//Mapping Authenticated User Class
				PrincipalUser principalUser = PrincipalUser.create(userEntity);
				
				//
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principalUser, 
																							null, 
																							principalUser.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (Exception e) {
			log.error("Error user authentication process");
		}
		
		
		//notify SpringBoot
		filterChain.doFilter(request, response);
		
	}
	
	public String getJwtResponse(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
