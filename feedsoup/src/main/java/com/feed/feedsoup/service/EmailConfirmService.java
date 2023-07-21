package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.dto.RegisterFormDTO;
import com.feed.feedsoup.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailConfirmService {

    private final RegisterRepository registerRepository;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendUserEmail;

    public EmailConfirmDTO sendMail(EmailConfirmDTO emailConfirmDTO) throws MailException {

        emailConfirmDTO.setCheckNum(this.randomKey());// 메일 인증번호
        emailConfirmDTO.setEmailStatus("N");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailConfirmDTO.getMemberId());// 보내는 대상
        simpleMailMessage.setSubject("feedsoup 회원가입 이메일 인증 코드");// 메일 제목
        simpleMailMessage.setFrom(sendUserEmail);// 보내는 사람의 메일 주소
        simpleMailMessage.setText("인증번호 : " + emailConfirmDTO.getCheckNum());// 내용

//      메일 발송
        javaMailSender.send(simpleMailMessage);

        return emailConfirmDTO;
    }

    public boolean findByEmailAndValidNum(EmailConfirmDTO sessionDTO, EmailConfirmDTO emailConfirmDTO, HttpSession httpSession) {
        if (emailConfirmDTO.getMemberId().equals(sessionDTO.getMemberId())){
            if (emailConfirmDTO.getCheckNum().equals(sessionDTO.getCheckNum())){
                sessionDTO.setEmailStatus("Y");
                this.createSession(sessionDTO,httpSession);
                return true;
            }
        }
        sessionDTO.setEmailStatus("N");
        this.createSession(sessionDTO,httpSession);
        return false;
    }

    public boolean findByEmailAndValidNum(EmailConfirmDTO sessionEmailConfirmDTO, RegisterFormDTO registerFormDTO) {
        if (sessionEmailConfirmDTO.getMemberId().equals(registerFormDTO.getMemberId())){
            if (sessionEmailConfirmDTO.getCheckNum().equals(registerFormDTO.getCheckNum())){
                return true;
            }
        }
        return false;
    }

    public void createSession(EmailConfirmDTO emailConfirmDTO, HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(300);
        httpSession.setAttribute("emailConfirm",emailConfirmDTO);
    }

    public boolean duplicateEmail(String memberId) {
        return registerRepository.duplicateEmail(memberId) != 0 ? false : true;
    }

    //  UUID 사용해서 10자리의 인증번호 추출
    public String randomKey() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 10);
    }

}
