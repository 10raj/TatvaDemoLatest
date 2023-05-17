

package com.example.Staff.config.kafka;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAthFilter extends OncePerRequestFilter{
	
	private final UserDetailsService userDetailsService;
	private final JWTService jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String jwtToken;
		final String userName;
		final String requestHeader = request.getHeader("AUTHORIZATION");
		
		if(requestHeader==null || ! requestHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
		}
		else {
			
		
		jwtToken = requestHeader.substring(7);
		log.info("Token in reader",jwtToken);
		userName= jwtUtil.getUserID(jwtToken);
		
		if(userName!= null || SecurityContextHolder.getContext().getAuthentication()!= null ) {
			//log.info(SecurityContextHolder.getContext().getAuthentication().toString());
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			if(jwtUtil.isValid(jwtToken, userDetails.getUsername())) {
				log.info(userDetails.getAuthorities().toString());
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, (Collection<? extends GrantedAuthority>) userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		}
	}
	

}
