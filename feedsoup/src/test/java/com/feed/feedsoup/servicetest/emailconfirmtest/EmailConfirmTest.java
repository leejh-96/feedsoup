package com.feed.feedsoup.servicetest.emailconfirmtest;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.service.EmailConfirmService;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmailConfirmTest {

    @Autowired
    private EmailConfirmService emailService;

    @Value("${spring.mail.username}")
    private String email;

    @Test
    @DisplayName("EmailConfirmService findByValidKey Test")
    void testFindByValidKey(){
        
        EmailConfirmDTO emailConfirmDTO = new EmailConfirmDTO();
        emailConfirmDTO.setEmail(email);

        boolean message = emailService.findByValidKey(emailConfirmDTO);

        assertTrue(message);
//        assertFalse(message);
    }

    @Test
    @DisplayName("EmailConfirmService findByValidNum Test")
    void testFindByValidNum(){

        EmailConfirmDTO emailConfirmDTO = new EmailConfirmDTO();
        emailConfirmDTO.setEmail(email);
        emailConfirmDTO.setCheckNum("f67afd18-3");

        boolean message = emailService.findByValidNum(emailConfirmDTO);

        assertTrue(message);
    }



}
