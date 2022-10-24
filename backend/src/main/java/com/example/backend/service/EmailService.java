package com.example.backend.service;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

public interface EmailService {
   void sendEmailActivationLinkToUser(String email, String link)throws MessagingException;
}
