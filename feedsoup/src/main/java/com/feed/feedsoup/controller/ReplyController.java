package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.LoginMemberDTO;
import com.feed.feedsoup.dto.MemberDTO;
import com.feed.feedsoup.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/reply/{boardNo}/{replyNo}")
    public void deleteReply(@PathVariable int boardNo, @PathVariable int replyNo){
        if (!replyService.deleteReply(boardNo, replyNo)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reply/{boardNo}")
    public String saveReply(@SessionAttribute("loginMember")LoginMemberDTO loginMemberDTO, @RequestParam String reply,
                            @PathVariable int boardNo){
        int page = 1;
        replyService.saveReply(boardNo,reply,loginMemberDTO.getMemberNo());
        return "redirect:/board/" + boardNo + "/" + page;
    }
}
