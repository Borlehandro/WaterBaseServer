package com.sibdever.water_base.security;

import com.sibdever.water_base.data.User;
import com.sibdever.water_base.data.UserAuthority;
import com.sibdever.water_base.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository repository;

    public CustomUserDetailsService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);
        return new CustomUserDetails(
                user.getUserAuthorities().stream().map(UserAuthority::toGrantedAuthority).collect(Collectors.toList()),
                user.getRole() != null ? user.getRole().name() : null,
                user.getUsername(),
                user.getPassword(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.isEnabled());
    }
}
