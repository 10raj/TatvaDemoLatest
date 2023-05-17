package com.example.AppGateWay.config;

import org.apache.commons.logging.Log;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.AppGateWay.service.JWTService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthManager implements ReactiveAuthenticationManager {
    final JWTService jwtService; 
    final private UserDetailsService userDetailsService;
    public AuthManager(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService=userDetailsService;
       // this.mongoUserDetailService = mongoUserDetailService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        return Mono.justOrEmpty(
                authentication
        )
        		.cast(BearerToken.class)
                .flatMap(auth -> {
                	String userName = jwtService.getUserID(auth.getCredentials()); 
                	log.info("AUthManager Called",userName);
                    Mono<UserDetails> user =Mono.just(userDetailsService.loadUserByUsername(userName));
                    log.info(user.toString());
                    return user.<Authentication>flatMap(u -> {
                       
                        if (u.getUsername() == null) {
                            log.info("User not found");
                            return Mono.error(new Exception("User not found"));
                        }
                        
                        if (jwtService.isValid(auth.getCredentials(), u.getUsername())) {
                            return Mono.just(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), u.getAuthorities()));
                        }
                        log.info("Invalid / Expired Token : {}", auth.getCredentials());
                        return Mono.error(new Exception("Invalid/Expired Token"));
                    });
                });
    }

}