package com.example.notification_service.services;

import com.example.notification_service.saga.SagaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SnsService snsService;

    public void sendBookingConfirmed(SagaEvent event) {

        String bookingId = (String) event.getPayload().get("bookingId");
        String email = (String) event.getPayload().get("email");

        String message = "✅ Booking Confirmed!\nBooking ID: " + bookingId;

        snsService.sendNotification(message);
    }

    public void sendBookingCancelled(SagaEvent event) {

        String bookingId = (String) event.getPayload().get("bookingId");

        String message = "❌ Booking Cancelled.\nRefund initiated.\nBooking ID: " + bookingId;

        snsService.sendNotification(message);
    }
}
