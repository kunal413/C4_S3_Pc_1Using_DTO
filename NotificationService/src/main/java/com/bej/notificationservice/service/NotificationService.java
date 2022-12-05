package com.bej.notificationservice.service;

import com.bej.notificationservice.config.MovieDTO;
import com.bej.notificationservice.domain.Notification;
import org.aspectj.weaver.ast.Not;


import java.util.List;

public interface NotificationService {

Notification getAllNotifications(String email);
    void saveNotifications(MovieDTO movieDTO);

}
