package com.weather.api.service;

import com.weather.api.domian.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class SmpleEmailService {

    //@Autowired
    //private MailCreatorService mailCreatorService;

    @Autowired
    private final JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation");
        try {
            javaMailSender.send(createMailMessage(mail));
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.error("Failed to precess email", e.getMessage(), e);
        }

    }

    private SimpleMailMessage createMailMessage (final Mail mail){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        Optional.ofNullable(mail.getToCc()).ifPresent(mailMessage::setCc);
        mailMessage.setSubject(mailMessage.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }


  /* Mail mailBuild = Mail.builder()
            .mailTo("Test@gmail.com")
            .toCc("mail@gmail.com")
            .subject(" ALL Field Test")
            .message("No message").build();*/
}
