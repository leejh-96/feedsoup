package com.feed.feedsoup.servicetest.emailconfirmtest;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.service.EmailConfirmService;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmailConfirmTest {

    @InjectMocks
    EmailConfirmService emailConfirmService;

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    HttpSession httpSession;

    private String email = "abcdefg1234@google.com";

    @Test
    @DisplayName("sendMail() 정상 작동 테스트(JavaMailSender 의존성 주입 X)")
    void testSendMail(){

        //given
        EmailConfirmDTO testEmailConfirmDTO = new EmailConfirmDTO();
        testEmailConfirmDTO.setMemberId(email);

        //when
        EmailConfirmDTO emailConfirmDTO = emailConfirmService.sendMail(testEmailConfirmDTO);

        //then
        assertThat(emailConfirmDTO).isInstanceOf(EmailConfirmDTO.class);
        assertThat(emailConfirmDTO.getMemberId()).isEqualTo(email).isInstanceOf(String.class);
        assertThat(emailConfirmDTO.getCheckNum().length()).isEqualTo(10);
        assertThat(emailConfirmDTO.getCheckNum()).isInstanceOf(String.class);
        assertThat(emailConfirmDTO.getEmailStatus()).isEqualTo("N");
        assertThat(emailConfirmDTO.getEmailStatus().length()).isEqualTo(1);

    }

    @Test
    @DisplayName("findByEmailAndValidNum() 정상 작동 테스트")
    void testFindByEmailAndValidNum(){

        //given
        String randomKey = this.randomKey();
        EmailConfirmDTO testStatusReturnY_1 = new EmailConfirmDTO(email,randomKey,"N");
        EmailConfirmDTO testStatusReturnY_2 = new EmailConfirmDTO(email,randomKey,"N");
        EmailConfirmDTO testStatusReturnN_1 = new EmailConfirmDTO(email, "0000000000", "N");
        EmailConfirmDTO testStatusReturnN_2 = new EmailConfirmDTO("abcdefg12@naver.com", randomKey, "N");

        //when
        //testStatusReturnY_1, testStatusReturnY_2
        boolean testStatusReturnY = emailConfirmService.findByEmailAndValidNum(testStatusReturnY_1, testStatusReturnY_2, httpSession);
        //testStatusReturnY_1, testStatusReturnN_1
        boolean testStatusReturnN1 = emailConfirmService.findByEmailAndValidNum(testStatusReturnY_1, testStatusReturnN_1, httpSession);
        //testStatusReturnN_1, testStatusReturnN_2
        boolean testStatusReturnN2 = emailConfirmService.findByEmailAndValidNum(testStatusReturnN_1, testStatusReturnN_2, httpSession);

        //then
        //testStatusReturnY => return true
        assertThat(testStatusReturnY).isTrue();
        assertThat(testStatusReturnY).isNotEqualTo(false);
        //testStatusReturnN1 => return false
        assertThat(testStatusReturnN1).isFalse();
        assertThat(testStatusReturnN1).isNotEqualTo(true);
        //testStatusReturnN2 => return false
        assertThat(testStatusReturnN2).isFalse();
        assertThat(testStatusReturnN2).isNotEqualTo(true);
    }

    @Test
    @DisplayName("randomKey() 정상 작동 테스트")
    void testRandomKey(){
        //given
        String randomKey = "";

        //when
        randomKey = emailConfirmService.randomKey();

        //then
        assertThat(randomKey).isInstanceOf(String.class).hasSize(10).isNotNull();
    }

    private String randomKey() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 10);
    }

}
