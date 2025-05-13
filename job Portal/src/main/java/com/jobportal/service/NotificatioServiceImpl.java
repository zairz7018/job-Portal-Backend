package com.jobportal.service;

import com.jobportal.dto.NotificationDto;
import com.jobportal.dto.NotificationStatus;
import com.jobportal.entity.Notification;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.NotificationRepository;
import com.jobportal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificatioServiceImpl implements NotificatioService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException {
        notificationDto.setId(Utilities.getNextSequence("notification"));
        notificationDto.setStatus(NotificationStatus.UNREAD);
        notificationDto.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificationDto.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId , NotificationStatus.UNREAD);
    }
}
