package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.service.EmailConfirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmailConfirmController {

    private final EmailConfirmService emailService;

    @ResponseBody
    @PostMapping("/sendMail")
    public Map<String,Object> sendMail(@Validated @ModelAttribute EmailConfirmDTO emailConfirmDTO,
                                       BindingResult bindingResult,HttpSession httpSession){

        Map<String,Object> status = new HashMap<>();
//      이메일 유효성 검사
        if (bindingResult.hasErrors()){
            status.put("result", "bindingResult");
            log.info("bindingResult");
            return status;
        }
//      이메일 중복 체크
        if (!emailService.duplicateEmail(emailConfirmDTO.getMemberId())){
            status.put("result", "duplicateEmail");
            log.info("duplicateEmail");
            return status;
        }

        EmailConfirmDTO sessionEmailConfirm = (EmailConfirmDTO)httpSession.getAttribute("emailConfirm");
        if (sessionEmailConfirm == null){
            emailService.createSession(emailService.sendMail(emailConfirmDTO),httpSession);
            status.put("result", "createSessionEmailConfirm");
            log.info("createSessionEmailConfirm");
            return status;
        }

        status.put("result","sessionTrue");
        log.info("sessionTrue");
        return status;
    }

    @ResponseBody
    @PostMapping("/validNum")
    public Map<String,Object> validNum(@SessionAttribute(value = "emailConfirm", required = false) EmailConfirmDTO sessionEmailConfirmDTO,
                                       @ModelAttribute EmailConfirmDTO emailConfirmDTO,
                                       HttpSession httpSession){

        Map<String,Object> status = new HashMap<>();

        if (sessionEmailConfirmDTO == null){
            status.put("result", "sessionEmailConfirmDTONull");
            return status;
        }

        if (emailService.findByEmailAndValidNum(sessionEmailConfirmDTO, emailConfirmDTO, httpSession)){
            status.put("result","validTrue");
        }else {
            status.put("result","validFalse");
        }

        return status;
    }

}
