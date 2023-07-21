package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.BoardUpdateFormDTO;
import com.feed.feedsoup.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardUpdateRepository {

    int deleteFile(@Param("boardNo") int boardNo, @Param("fileNo") int fileNo);

    FileDTO findByFile(@Param("boardNo") int boardNo,@Param("fileNo") int fileNo);

    List<FileDTO> findByFileList(int boardNo);

    BoardUpdateFormDTO findByBoard(int boardNo);

    void updateBoard(BoardUpdateFormDTO boardUpdateFormDTO);
}
