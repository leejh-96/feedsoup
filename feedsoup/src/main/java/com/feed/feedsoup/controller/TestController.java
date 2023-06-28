package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.service.EmailConfirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@Controller
public class TestController {

private final EmailConfirmService emailConfirmService;
    
    @ResponseBody
//    @PostMapping("/sendMail")
    public Map<String,Object> sendMail(@Validated @ModelAttribute EmailConfirmDTO emailConfirmDTO,
                                       BindingResult bindingResult,
//                                       HttpServletRequest httpServletRequest,
                                       HttpSession httpSession){
        Map<String,Object> status = new HashMap<>();
        if (bindingResult.hasErrors()){
            status.put("result", "bindingResult");
            return status;
        }
        log.info("emailConfirmDTO : {}",emailConfirmDTO);
//        HttpSession session = httpServletRequest.getSession(false);
        // 첫 접속시 SESSION이 NULL 이면 여기서 초기화
        if (httpSession == null){

//            if (httpSession == null){
//                emailService.createSession(emailService.sendMail(emailConfirmDTO),httpSession);
//                log.info("createSession");
//                status.put("result", "createSession");
//                return status;
//            }




            log.info("emailConfirmDTO 바인딩: {}",emailConfirmDTO);
            status.put("emailConfirmDTO 바인딩",emailConfirmDTO.toString());
            status.put("emailConfirmDTO hashCode 바인딩",emailConfirmDTO.hashCode());
            status.put("result","sessionCreate");
//            session = httpServletRequest.getSession(true);
            httpSession.setMaxInactiveInterval(20);
            EmailConfirmDTO sendMail = emailConfirmService.sendMail(emailConfirmDTO);
            httpSession.setAttribute("emailConfirm",sendMail);
            log.info("sendMail 세션: {}",sendMail);
            status.put("sendMail 세션",sendMail.toString());
            status.put("sendMail hashCode 세션",sendMail.hashCode());
            boolean equals = emailConfirmDTO.equals(sendMail);
            boolean hashcode = emailConfirmDTO.hashCode() == sendMail.hashCode()?true:false;
            status.put("equals ",equals);
            status.put("hashcode ",hashcode);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            status.put("maxInactiveInterval",httpSession.getMaxInactiveInterval());
            status.put("creationTime",simpleDateFormat.format(new Date(httpSession.getCreationTime())));
            status.put("LastAccessedTime",simpleDateFormat.format(new Date(httpSession.getLastAccessedTime())));
            status.put("sessionNotNull : {}",httpSession.getId());
            return status;
        }

        EmailConfirmDTO emailConfirm1 = (EmailConfirmDTO) httpSession.getAttribute("emailConfirm");
        // SESSION의 유효시간이 지나서 브라우저에서 새로운세션이 할당되었지만 emailConfirm 속성이 없기 때문에 새로 저장
        if (emailConfirm1 == null){

            status.put("create emailConfirmDTO","emailConfirmDTO가 NULL입니다.");
            log.info("세션이 유효하지 않지만 브라우저에서 새롭게 세션은 생성되었음 하지만 emailConfrim속성은 없음");
            log.info("emailConfirmDTO 바인딩: {}",emailConfirmDTO);
            status.put("emailConfirmDTO 바인딩",emailConfirmDTO.toString());
            // 시간도 다시 지정해야함
            httpSession.setMaxInactiveInterval(20);
            EmailConfirmDTO sendMail = emailConfirmService.sendMail(emailConfirmDTO);
            httpSession.setAttribute("emailConfirm",sendMail);
            log.info("sendMail 세션: {}",sendMail);
            status.put("sendMail 세션",sendMail.toString());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            status.put("maxInactiveInterval",httpSession.getMaxInactiveInterval());
            status.put("creationTime",simpleDateFormat.format(new Date(httpSession.getCreationTime())));
            status.put("LastAccessedTime",simpleDateFormat.format(new Date(httpSession.getLastAccessedTime())));
            status.put("sessionNotNull : {}",httpSession.getId());
            return status;
        }
        int maxInactiveInterval = httpSession.getMaxInactiveInterval();
        Date creationTime = new Date(httpSession.getCreationTime());
        Date LastAccessedTime = new Date(httpSession.getLastAccessedTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        status.put("maxInactiveInterval",httpSession.getMaxInactiveInterval());
        status.put("creationTime",simpleDateFormat.format(new Date(httpSession.getCreationTime())));
        status.put("LastAccessedTime",simpleDateFormat.format(new Date(httpSession.getLastAccessedTime())));
        status.put("sessionNotNull : {}",httpSession.getId());
        return status;
    }

    @ResponseBody
    @PostMapping("/toSendMail")
    public String toSendMail(HttpServletRequest httpServletRequest,HttpSession httpSession,
                             @SessionAttribute(value = "emailConfirm", required = false) EmailConfirmDTO emailConfirmDTO){
//        EmailConfirmDTO emailConfirm = (EmailConfirmDTO) httpServletRequest.getAttribute("emailConfirm");
//        Object emailConfirm = httpSession.getAttribute("emailConfirm");

        if (httpSession == null){
            return "httpSession null";

//            if (httpSession == null){
//                status.put("result","sessionNull");
//                return status;
//            }



        }


        if (emailConfirmDTO == null){
            return "emailConfirmDTO null";
        }

        return emailConfirmDTO.toString();
    }


}
