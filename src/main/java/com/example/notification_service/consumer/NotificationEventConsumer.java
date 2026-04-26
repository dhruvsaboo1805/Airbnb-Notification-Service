package com.example.notification_service.consumer;

import com.example.notification_service.saga.SagaEvent;
import com.example.notification_service.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventConsumer {

    @Value("${SAGA_QUEUE_VALUE}")
    private String SAGA_QUEUE;

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @Scheduled(fixedDelay = 500)
    public void consumeEvents() {
        try {
            String eventJson = redisTemplate.opsForList().leftPop(SAGA_QUEUE , 1 , TimeUnit.SECONDS);
            if (eventJson != null) {
                SagaEvent sagaEvent = objectMapper.readValue(eventJson, SagaEvent.class);

                handleEvent(sagaEvent);
            }
        } catch (Exception e) {
            log.error("Error in notification consumer: {}", e.getMessage());
        }
    }

    private void handleEvent(SagaEvent event) {

        switch (event.getEventType()) {

            case "BOOKING_CONFIRMED":
                notificationService.sendBookingConfirmed(event);
                break;

            case "BOOKING_CANCELLED":
                notificationService.sendBookingCancelled(event);
                break;

            default:
                // ignore other events
                break;
        }
    }


}
