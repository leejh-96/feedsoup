package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.BoardCategoryDTO;
import com.feed.feedsoup.dto.BoardFormDTO;
import com.feed.feedsoup.dto.BoardOptionDTO;
import com.feed.feedsoup.dto.FileUploadDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardFormRepository {
    List<BoardCategoryDTO> findByBoardCategory();

    List<BoardOptionDTO> findByBoardOption(int boardCategoryNo);

    void saveBoard(BoardFormDTO boardFormDTO);

    void saveFile(List<FileUploadDTO> fileUploadDTOList);
}
