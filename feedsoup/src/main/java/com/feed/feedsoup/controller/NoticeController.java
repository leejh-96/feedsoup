package com.feed.feedsoup.controller;

import com.feed.feedsoup.service.NoticeService;
import com.feed.feedsoup.util.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{page}")
    public String noticeList(@PathVariable int page,Model model){

        PageInfo pageInfo = new PageInfo(page,noticeService.findByNoticeCount());
        pageInfo.pageSettings();

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("noticeList",noticeService.findByNoticeList(pageInfo.getOffset(),pageInfo.getLimit()));
        return "notice/noticeList";

    }

}
