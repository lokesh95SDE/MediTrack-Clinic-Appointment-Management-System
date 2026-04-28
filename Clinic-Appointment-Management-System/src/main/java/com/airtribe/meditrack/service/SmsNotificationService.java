package com.airtribe.meditrack.service;
import com.airtribe.meditrack.Interface.Observer;

public class SmsNotificationService implements Observer {

    @Override
    public void update(String message) {
        System.out.println("SMS Notification: " + message);
    }
}