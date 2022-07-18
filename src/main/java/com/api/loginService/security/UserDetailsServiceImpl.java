package com.api.loginService.security;

import com.api.loginService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = userRepository.findByCpf(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User with username " + username +" not found"
                )
        );

        return new UserDetailsData(usuario);
    }
}
