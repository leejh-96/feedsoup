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
        // 이메일을 매개변수로 받아서 유효한 시간내에 인증받은 기록이 있다면 
        // 인증메일을 보내지 않고 false를 리턴,
        // 유효한 시간내에 인증받은 기록이 없다면
        // 인증메일을 보내고 true를 리턴
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
        // 이메일과 인증번호를 매개변수로 받아서 유효한 시간내에 인증번호를 인증해서 일치한다면
        // true를 리턴 후 email_status 컬럼을 'Y'로 update,
        // 유효한 시간이 아니며, 인증번호가 일치하지 않는다면
        // false를 리턴
        boolean message = emailService.findByValidNum(emailConfirmDTO);

        assertTrue(message);
    }



}
