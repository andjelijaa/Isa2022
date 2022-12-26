package com.example.backend.service;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

public interface EmailService {
   void sendEmail(String to, String subject, String body);
}
