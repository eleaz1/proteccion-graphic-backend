package com.abivan.graphics.service.imp;

import com.abivan.graphics.domain.Users;
import com.abivan.graphics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Users usuario = userRepository.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username + "'");
        }

        List<GrantedAuthority> authorities= usuario.getRols()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getDescription()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authorities);
    }
}
