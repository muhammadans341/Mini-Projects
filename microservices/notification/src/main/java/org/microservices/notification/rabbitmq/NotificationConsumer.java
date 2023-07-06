package org.microservices.notification.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservices.clients.notification.NotificationRequest;
import org.microservices.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;
    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumeNotification(@Payload NotificationRequest notificationRequest){
        log.info("Converting back to object");

        log.info("Consuming notification request {}",notificationRequest);
        //notificationService.sendNotification((NotificationRequest) notificationRequest);
    }

}
