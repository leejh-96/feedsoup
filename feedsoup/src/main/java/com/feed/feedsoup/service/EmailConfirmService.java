package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.repository.EmailConfirmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailConfirmService {

    private final JavaMailSender javaMailSender;

    private final EmailConfirmRepository emailRepository;

    @Value("${spring.mail.username}")
    private String sendUserEmail;

    public boolean findByValidKey(EmailConfirmDTO emailConfirmDTO){
        EmailConfirmDTO valid = emailRepository.findByValidKey(emailConfirmDTO);
        if (valid == null){
            return this.sendMail(emailConfirmDTO.getEmail());
        }
        return false;
    }
    public boolean findByValidNum(EmailConfirmDTO emailConfirmDTO) {
        EmailConfirmDTO valid = emailRepository.findByValidNum(emailConfirmDTO);
        if (valid == null){
            return false;
        }
        this.update(emailConfirmDTO);
        return true;
    }

//  메일을 보내는 대상, 메일 제목, 보내는 사람의 메일 주소, 메일 제목, 메일 내용, 메일 인증번호 설정
    public boolean sendMail(String toUserEmail) {

        String checkNum = this.randomKey();// 메일 인증번호
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toUserEmail);// 보내는 대상
        simpleMailMessage.setSubject("feedsoup 회원가입 이메일 인증 코드");// 메일 제목
        simpleMailMessage.setFrom(sendUserEmail);// 보내는 사람의 메일 주소
        simpleMailMessage.setText("인증번호 : " + checkNum);// 내용

        log.info("randomKey : {}", checkNum);

        try {
//          메일 발송
            javaMailSender.send(simpleMailMessage);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
//      DB에 이메일과 인증번호를 임시저장
        this.save(toUserEmail, checkNum);
        return true;
    }

    @Transactional
    public void update(EmailConfirmDTO emailConfirmDTO){
        emailRepository.update(emailConfirmDTO);
    }
    @Transactional
    public void save(String toUserEmail, String checkNum) {
        emailRepository.save(new EmailConfirmDTO(toUserEmail, checkNum));
    }

    //  UUID 사용해서 10자리의 인증번호 추출
    public String randomKey() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 10);
    }


}
