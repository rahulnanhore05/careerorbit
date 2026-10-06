package io.rahulnanhore.consumer;

import io.rahulnanhore.event.ApplicationStatusChangedEvent;
import io.rahulnanhore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationKafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "application.status.changed",
            groupId = "notification-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenStatusChanged(ApplicationStatusChangedEvent event){
        notificationService.sendStatusChangedEmail(event);
    }
}
