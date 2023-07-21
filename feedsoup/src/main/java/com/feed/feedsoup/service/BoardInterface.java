package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.BoardCategoryDTO;
import com.feed.feedsoup.dto.BoardDetailDTO;

import java.util.List;

public interface BoardInterface {
    List<BoardCategoryDTO> findByBoardCategory();

    BoardDetailDTO findByBoard(int boardNo);

}
