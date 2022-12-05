package com.bej.notificationservice.controller;

import com.bej.notificationservice.service.NotificationService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v3")
public class NotificationController {
    private NotificationService notificationService;
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> notifications(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String email = claims.getSubject();
        System.out.println("user email from claims(notification) :: " + claims.getSubject());
        System.out.println("email " + email);
        return new ResponseEntity<>(notificationService.getAllNotifications(email), HttpStatus.OK);
    }
}
