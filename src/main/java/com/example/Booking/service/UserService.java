package com.example.Booking.service;

import com.example.Booking.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;

    public String getUserRole(String username) {
        return userEntityRepository.getUserRole(username);
    }

    public String getUsernameFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }
}
