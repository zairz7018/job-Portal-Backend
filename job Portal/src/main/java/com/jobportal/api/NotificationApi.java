package com.jobportal.api;

import com.jobportal.entity.Notification;
import com.jobportal.service.NotificatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notification")
@Validated
public class NotificationApi {
    @Autowired
    private NotificatioService notificatioService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>>getNotifications(@PathVariable Long userId) {
        return new ResponseEntity<>(notificatioService.getUnreadNotifications(userId), HttpStatus.OK);



    }
}
