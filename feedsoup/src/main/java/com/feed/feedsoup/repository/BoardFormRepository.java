package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardFormRepository {

    List<BoardOptionDTO> findByBoardOption(int boardCategoryNo);

    void saveBoard(BoardFormDTO boardFormDTO);

    void saveFile(List<FileUploadDTO> fileUploadDTOList);

}
