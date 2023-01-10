package com.example.Booking.security;

import com.example.Booking.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("No user"));
    }
}
