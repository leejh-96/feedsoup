package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.RegisterFormDTO;
import com.feed.feedsoup.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService service;

    @GetMapping("/form")
    public String register(){
        return "register/registerForm";
    }

    @PostMapping("/save")
    public String registerSave(@Validated @ModelAttribute RegisterFormDTO register,
                               BindingResult bindingResult,
                               RedirectAttributes redirect){
        //RegisterFormDTO 유효성검증
        if (bindingResult.hasErrors()){
            return "register/registerForm";
        }

        log.info("register : {}",register);

        service.registerSave(register);
        redirect.addFlashAttribute("status","true");

        //페이지 만들어지면 경로수정하기
        return "redirect:/register/form";
    }




}
