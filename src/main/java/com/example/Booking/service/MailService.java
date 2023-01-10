package com.example.Booking.service;

import com.example.Booking.model.api.BookingReservationDetailsDTO;
import com.example.Booking.model.api.RentalCarsEditReservationParams;
import com.example.Booking.model.api.RentalCarsReservationMinimumDTO;
import com.example.Booking.model.api.RentalCarsReservationParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private ReservationService reservationService;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${admin.mail}")
    private String adminMail;

    @Autowired
    public MailService(@Lazy ReservationService reservationService, JavaMailSender javaMailSender) {
        this.reservationService = reservationService;
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendMessageWithDeleteInfo(int reservationId) {
        BookingReservationDetailsDTO reservationById =
                reservationService.getReservationById(reservationId);
        String driverMail = reservationById.getDrivers().get(0).getEmail();
        sendSimpleMessage(driverMail, "Rezerwacja usunięta", "Rezerwacja usunięta");
    }

    public void sendMessageWithConfirmation(RentalCarsReservationParams reservationParams,
                                            RentalCarsReservationMinimumDTO carsReservationMinimumDTO) {
        String driverMail = reservationParams.getDrivers().get(0).getEmail();
        sendSimpleMessage(driverMail, "Rezerwacja utworzona " +
                carsReservationMinimumDTO.getSupplierReservationReference(), "Rezerwacja utworzona");
    }

    public void sendMessageWithEditInfo(RentalCarsEditReservationParams editReservationParams,
                                        RentalCarsEditReservationParams carsEditReservationParams) {
        String driverMail = editReservationParams.getDrivers().get(0).getEmail();
        sendSimpleMessage(driverMail, "Rezerwacja edytowana " +
                carsEditReservationParams.getSupplierReservationReference(), "Rezerwacja edytowana");
    }

    public void sendMailToAdminWithNonAuthorizedAccess() {
        sendSimpleMessage(adminMail, "Niedozwolony dostęp", "Niedozwolony dostęp");
    }
}
