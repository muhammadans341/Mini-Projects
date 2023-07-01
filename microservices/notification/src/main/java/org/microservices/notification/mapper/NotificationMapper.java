package org.microservices.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.microservices.clients.notification.NotificationRequest;
import org.microservices.notification.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",imports = {java.time.LocalDateTime.class})
public interface NotificationMapper {
    @Mapping(target="sender", constant = "NotifClient")
    @Mapping(target = "sentAt" ,expression = "java(LocalDateTime.now())")
    NotificationEntity toNotificationEntity(NotificationRequest notificationRequest);
}
