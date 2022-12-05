package com.bej.notificationservice.domain;


import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notification {
    @Id
    private String email;
    private String notificationMessage;
    private JSONObject movieNames;

    public Notification() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public JSONObject getMovieNames() {
        return movieNames;
    }

    public void setMovieNames(JSONObject movieNames) {
        this.movieNames = movieNames;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "email='" + email + '\'' +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", movieNames=" + movieNames +
                '}';
    }
}
