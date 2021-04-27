package com.example.practice15.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest {

    private EmailService emailService;
    @MockBean
    private JavaMailSender mailSender;
    @Captor
    private ArgumentCaptor<SimpleMailMessage> captor;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    @Test
    public void sendEmail() {
        String subject = "Some subject";
        String text = "Some text";
        emailService.sendSimpleEmail(subject, text);

        verify(mailSender, times(1))
                .send(ArgumentMatchers.any(SimpleMailMessage.class));
        verify(mailSender,times(1))
                .send(captor.capture());

        SimpleMailMessage captured = captor.getValue();
        Assertions.assertEquals(subject, captured.getSubject());
        Assertions.assertEquals(text, captured.getText());
    }
}
