package com.bej.notificationservice.service;

import com.bej.notificationservice.config.MovieDTO;
import com.bej.notificationservice.domain.Notification;
import com.bej.notificationservice.repository.NotificationRepository;

import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification getAllNotifications(String email) {
        return notificationRepository.findById(email).get();
    }

    @RabbitListener(queues = "movie.queue")
    @Override
    public void saveNotifications(MovieDTO movieDTO) {
        Notification notification = new Notification();
        String email = movieDTO.getJsonObject().get("email").toString();
        if (notificationRepository.findById(email).isEmpty()) {
            notification.setEmail(email);
        }
        notification.setNotificationMessage("The list of not watched movies");
        notification.setMovieNames(movieDTO.getJsonObject());
        notificationRepository.save(notification);
    }

}

