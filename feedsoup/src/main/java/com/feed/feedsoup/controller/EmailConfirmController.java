package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.service.EmailConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class EmailConfirmController {

    private final EmailConfirmService emailService;
    @ResponseBody
    @PostMapping("/sendMail")
    public boolean sendMail(@ModelAttribute EmailConfirmDTO emailConfirmDTO){

        return emailService.findByValidKey(emailConfirmDTO);
    }
    @ResponseBody
    @PostMapping("/validNum")
    public boolean validNum(@ModelAttribute EmailConfirmDTO emailConfirmDTO){
        return emailService.findByValidNum(emailConfirmDTO);
    }

}
