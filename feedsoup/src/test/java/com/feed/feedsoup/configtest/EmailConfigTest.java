package com.feed.feedsoup.configtest;

import com.feed.feedsoup.config.EmailConfig;
import com.feed.feedsoup.service.EmailConfirmService;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

@Slf4j
@SpringBootTest
public class EmailConfigTest {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private EmailConfirmService emailService;

    @Test
    @DisplayName("EmailConfig Test")
    void testEmailConfig(){

        int result = 10;
        JavaMailSender javaMailSender = emailConfig.javaMailSender();
        Properties mailProperties = emailConfig.getMailProperties();
        String randomKey = emailService.randomKey();

        log.info("javaMailSender() : {}",javaMailSender);
        log.info("getMailProperties() : {}",mailProperties.toString());
        log.info("randomKey() : {}",randomKey);

        assertTrue(javaMailSender instanceof JavaMailSender);
        assertTrue(mailProperties instanceof Properties);
        assertEquals(result,randomKey.length());
    }



}
