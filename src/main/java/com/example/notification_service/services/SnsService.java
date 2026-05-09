package com.example.notification_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class SnsService {

    private final SnsClient snsClient;
    private final String topicArn;

    public SnsService(
            @Value("${AWS_REGION}") String region,
            @Value("${AWS_SNS_TOPIC_ARN}") String topicArn) {

        this.snsClient = SnsClient.builder()
                .region(Region.of(region))
                .build();

        this.topicArn = topicArn;
    }

    public void sendNotification(String message) {

        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();

//        snsClient.publish(request);
        PublishResponse response = snsClient.publish(request);

        System.out.println("SNS MESSAGE SENT: " + response.messageId());
    }
}
