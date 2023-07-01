package org.microservices.notification.service;

import org.microservices.clients.notification.NotificationRequest;
import org.microservices.notification.repository.NotificationRepository;
import org.microservices.notification.entity.NotificationEntity;
import org.microservices.notification.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;
    public NotificationService(NotificationMapper notificationMapper, NotificationRepository notificationRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(NotificationRequest notificationRequest){
        NotificationEntity notificationEntity =notificationMapper.toNotificationEntity(notificationRequest);
        notificationRepository.save(notificationEntity);
    }
}
