package com.example.Booking.service.api;

import com.example.Booking.entity.UserEntity;
import com.example.Booking.model.api.RentalCarsLoginResult;
import com.example.Booking.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final RestTemplate restTemplate;
    private final UserEntityRepository userEntityRepository;
    @Value("${api.main.url}")
    private String apiMainUrl;
    @Value("${api.login.url}")
    private String apiLoginUrl;
    @Value("${api.user.role}")
    private String userRole;
    @Value("${api.password}")
    private String apiPassword;

    public String createToken() {
        UserEntity user = userEntityRepository.findByRole(userRole)
                .orElseThrow(() -> new UsernameNotFoundException("No user"));
        System.out.println("user = " + user);
        Map<String, String> body = new HashMap<>();
        body.put("username", user.getUsername());
        body.put("password", apiPassword);

        ResponseEntity<RentalCarsLoginResult> token =
                restTemplate.postForEntity(apiMainUrl + apiLoginUrl, body, RentalCarsLoginResult.class);

        return Objects.requireNonNull(token.getBody()).getToken();
    }

    public HttpEntity createAuthorizationHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + createToken());

        return new HttpEntity<>(httpHeaders);
    }


}
