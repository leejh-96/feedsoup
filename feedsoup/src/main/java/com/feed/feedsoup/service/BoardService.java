package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.*;
import com.feed.feedsoup.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService implements BoardInterface{

    private final BoardRepository boardRepository;

    @Override
    public List<BoardCategoryDTO> findByBoardCategory() {
        return boardRepository.findByBoardCategory();
    }

    @Override
    public BoardDetailDTO findByBoard(int boardNo) {
        return boardRepository.findByBoard(boardNo);
    }

    public int findByBoardCount(SearchDTO searchDTO){
        return boardRepository.findByBoardCount(searchDTO);
    }

    public List<BoardListDTO> findByBoardList(int offset, int limit, SearchDTO searchDTO) {
        return boardRepository.findByBoardList(searchDTO,offset,limit);
    }

    public void updateHit(int boardNo) {
        boardRepository.updateHit(boardNo);
    }

    public int findByReplyCount(int boardNo) {
        return boardRepository.findByReplyCount(boardNo);
    }

    public List<ReplyDetailDTO> findByReplyList(int offset, int limit, int boardNo) {
        return boardRepository.findByReplyList(offset,limit,boardNo);
    }

    public Cookie createCookie() {
        Cookie cookie = new Cookie("hitCookie","true");
        cookie.setMaxAge(300);
        return cookie;
    }

    public boolean deleteBoard(int boardNo) {
        return boardRepository.deleteBoard(boardNo) > 0 ? true : false;
    }
}
