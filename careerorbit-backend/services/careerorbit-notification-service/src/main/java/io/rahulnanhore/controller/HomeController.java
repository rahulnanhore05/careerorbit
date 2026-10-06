package io.rahulnanhore.controller;

import io.rahulnanhore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HomeController {

    private final NotificationService notificationService;

    @GetMapping("/send")
    public String sendMail() throws Exception {
        notificationService.sendMail("ojack5040@gmail.com", "Test", "This is a test email");
        return "Mail sent successfully";
    }
}
