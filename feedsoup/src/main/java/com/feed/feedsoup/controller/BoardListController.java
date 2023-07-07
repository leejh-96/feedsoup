package com.feed.feedsoup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardListController {

    @GetMapping("/list")
    public String list(){
        return "board/boardList";
    }
}
