package org.microservices.notification.controller;

import org.microservices.clients.notification.NotificationRequest;
import org.microservices.notification.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notify")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        notificationService.sendNotification(notificationRequest);

    }
}
