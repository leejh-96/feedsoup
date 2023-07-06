package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.BoardCategoryDTO;
import com.feed.feedsoup.dto.BoardOptionDTO;
import com.feed.feedsoup.repository.BoardFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFormService {

    private final BoardFormRepository boardFormRepository;
    public List<BoardCategoryDTO> findByBoardCategory() {
        return boardFormRepository.findByBoardCategory();
    }

    public List<BoardOptionDTO> findByBoardOption(int boardCategoryNo) {
        return boardFormRepository.findByBoardOption(boardCategoryNo);
    }
}
