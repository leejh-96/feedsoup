package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {

    List<BoardCategoryDTO> findByBoardCategory();

    int findByBoardCount(@Param("searchDTO") SearchDTO searchDTO);

    int findByReplyCount(int boardNo);

    void updateHit(int boardNo);

    BoardDetailDTO findByBoard(int boardNo);

    List<BoardListDTO> findByBoardList(@Param("searchDTO") SearchDTO searchDTO,@Param("offset") int offset,@Param("limit") int limit);

    List<ReplyDetailDTO> findByReplyList(@Param("offset") int offset, @Param("limit") int limit, @Param("boardNo") int boardNo);

    int deleteBoard(int boardNo);
}
