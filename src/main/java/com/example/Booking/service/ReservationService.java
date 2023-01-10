package com.example.Booking.service;

import com.example.Booking.model.api.BookingReservationDetailsDTO;
import com.example.Booking.model.api.RentalCarsCancelReservationResult;
import com.example.Booking.model.api.RentalCarsEditReservationParams;
import com.example.Booking.model.api.RentalCarsReservationMinimumDTO;
import com.example.Booking.model.api.RentalCarsReservationParams;
import com.example.Booking.model.api.RentalCarsSearchRentalOffersResult;
import com.example.Booking.model.api.SearchOffersRequiredParametersDTO;
import com.example.Booking.service.api.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final RestTemplate restTemplate;
    private final TokenService tokenService;
    private final MailService mailService;
    private final UserService userService;
    @Value("${api.main.url}")
    private String apiMainUrl;
    @Value("${api.getReservationById.url}")
    private String apiGetReservationByIdUrl;
    @Value("${api.deleteReservationById.url}")
    private String apiDeleteReservationByIdUrl;
    @Value("${api.editReservationById.url}")
    private String apiEditReservationByIdUrl;
    @Value("${api.createReservation.url}")
    private String apiCreateReservationUrl;
    @Value("${api.searchOffers.url}")
    private String apiSearchOffersUrl;
    @Value("${role.with.access.to.delete.reservation}")
    private String roleWithAccessToDeleteReservation;
    @Value("${role.with.access.to.create.reservation}")
    private String roleWithAccessToCreateReservation;
    @Value("${role.with.access.to.edit.reservation}")
    private String roleWithAccessToEditReservation;


    public BookingReservationDetailsDTO getReservationById(int reservationId) {
        return restTemplate.exchange(apiMainUrl + apiGetReservationByIdUrl + reservationId, HttpMethod.GET,
                tokenService.createAuthorizationHeaders(), BookingReservationDetailsDTO.class).getBody();
    }

    public RentalCarsCancelReservationResult deleteReservationById(int reservationId) {
        if (userService.getUserRole(
                userService.getUsernameFromAuthentication()).equals(roleWithAccessToDeleteReservation)) {
            RentalCarsCancelReservationResult rentalCarsCancelReservationResult =
                    restTemplate.exchange(apiMainUrl + apiDeleteReservationByIdUrl + reservationId, HttpMethod.DELETE,
                            tokenService.createAuthorizationHeaders(), RentalCarsCancelReservationResult.class).getBody();
            if (rentalCarsCancelReservationResult != null) {
                mailService.sendMessageWithDeleteInfo(reservationId);
            }
            return rentalCarsCancelReservationResult;
        } else {
            mailService.sendMailToAdminWithNonAuthorizedAccess();
            throw new RuntimeException("dupa");
        }
    }

    public RentalCarsEditReservationParams editReservationById(RentalCarsEditReservationParams editReservationParams) {
        if (userService.getUserRole(
                userService.getUsernameFromAuthentication()).equals(roleWithAccessToEditReservation)) {
            RentalCarsEditReservationParams carsEditReservationParams =
                    restTemplate.exchange(apiMainUrl + apiEditReservationByIdUrl, HttpMethod.PUT,
                            createHttpEntityForEditReservation(editReservationParams),
                            RentalCarsEditReservationParams.class).getBody();
            if (carsEditReservationParams != null) {
                mailService.sendMessageWithEditInfo(editReservationParams, carsEditReservationParams);
            }
            return carsEditReservationParams;
        } else {
            mailService.sendMailToAdminWithNonAuthorizedAccess();
            throw new RuntimeException("brak uprawnień");
        }
    }

    private HttpEntity<RentalCarsEditReservationParams> createHttpEntityForEditReservation
            (RentalCarsEditReservationParams editReservationParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + tokenService.createToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(editReservationParams, headers);
    }

    public RentalCarsReservationMinimumDTO createReservation(RentalCarsReservationParams reservationParams) {
        if (userService.getUserRole(
                userService.getUsernameFromAuthentication()).equals(roleWithAccessToCreateReservation)) {
            RentalCarsReservationMinimumDTO carsReservationMinimumDTO =
                    restTemplate.exchange(apiMainUrl + apiCreateReservationUrl, HttpMethod.POST,
                            createHttpEntityForCreateReservation(reservationParams),
                            RentalCarsReservationMinimumDTO.class).getBody();
            if (carsReservationMinimumDTO != null) {
                mailService.sendMessageWithConfirmation(reservationParams, carsReservationMinimumDTO);
            }
            return carsReservationMinimumDTO;
        } else throw new RuntimeException("brak uprawnień");
    }

    private HttpEntity<RentalCarsReservationParams> createHttpEntityForCreateReservation
            (RentalCarsReservationParams reservationParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + tokenService.createToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(reservationParams, headers);
    }

    public RentalCarsSearchRentalOffersResult searchOffers(SearchOffersRequiredParametersDTO requiredParametersDTO) {
        String urlWithParameters = "?pickUpDate=" + requiredParametersDTO.getPickUpDate() +
                "&dropOffDate=" + requiredParametersDTO.getDropOffDate() +
                "&supplierPickUpLocCode=" + requiredParametersDTO.getSupplierPickUpLocCode() +
                "&supplierDropOffLocCode=" + requiredParametersDTO.getSupplierDropOffLocCode();

        return restTemplate.exchange(apiMainUrl + apiSearchOffersUrl + urlWithParameters, HttpMethod.GET,
                tokenService.createAuthorizationHeaders(), RentalCarsSearchRentalOffersResult.class).getBody();
    }
}
