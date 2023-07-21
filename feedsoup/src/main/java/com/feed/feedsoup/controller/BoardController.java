package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.SearchDTO;
import com.feed.feedsoup.service.BoardService;
import com.feed.feedsoup.util.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{boardNo}")
    public void deleteBoard(@PathVariable int boardNo){
        if (!boardService.deleteBoard(boardNo)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/board")
    public String list(@Validated @ModelAttribute SearchDTO searchDTO, BindingResult bindingResult,
                       @RequestParam(defaultValue = "1") int page, Model model) {

        if (bindingResult.hasErrors()){
            return "board/boardList";
        }

        PageInfo pageInfo = new PageInfo(page,boardService.findByBoardCount(searchDTO));
        pageInfo.pageSettings();

        model.addAttribute("searchDTO",searchDTO);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("boardList",boardService.findByBoardList(pageInfo.getOffset(),pageInfo.getLimit(),searchDTO));
        model.addAttribute("boardCategoryList",boardService.findByBoardCategory());
        return "board/boardList";
    }

    @GetMapping("/board/{boardNo}/{page}")
    public String details(@CookieValue(value="hitCookie", required=false) Cookie hitCookie,
                          @PathVariable int boardNo, @PathVariable int page,
                          HttpServletResponse response, Model model){

        if (hitCookie == null){
            boardService.updateHit(boardNo);
            response.addCookie(boardService.createCookie());
        }

        PageInfo pageInfo = new PageInfo(page, boardService.findByReplyCount(boardNo),5,5);
        pageInfo.pageSettings();

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("boardDetailDTO",boardService.findByBoard(boardNo));
        model.addAttribute("replyList",boardService.findByReplyList(pageInfo.getOffset(),pageInfo.getLimit(),boardNo));
        return "board/boardDetail";
    }

}
