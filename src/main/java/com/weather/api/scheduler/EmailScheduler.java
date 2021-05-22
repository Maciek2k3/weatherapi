package com.weather.api.scheduler;

import com.weather.api.config.AdminConfig;
import com.weather.api.domian.Mail;
import com.weather.api.repo.BookingItemRepo;
import com.weather.api.service.SmpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private final SmpleEmailService smpleEmailService;
    private final BookingItemRepo bookingItemRepo;
    private final AdminConfig adminConfig;
    private static final String SUBJECT = "Booking items once a day";


    @Scheduled(cron = "0 22 12 ? * *")
    public void sendInformationEmail() {
        Long size=bookingItemRepo.count();
        String itemOrItems;
        if (size == 1) {
            itemOrItems = "Booking item";
            smpleEmailService.send(new Mail(adminConfig.getAdminMail(), "", SUBJECT,
                    "Currently in database you've got: " + size + itemOrItems));
        } else {
            itemOrItems = "Booking items";
            smpleEmailService.send(new Mail(adminConfig.getAdminMail(), adminConfig.getAdminMail(), SUBJECT,
                    "Currently in database you've got : " + size+" " + itemOrItems));
        }
    }
}