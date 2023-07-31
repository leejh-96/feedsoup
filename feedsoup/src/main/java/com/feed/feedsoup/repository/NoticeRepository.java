package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.BoardListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeRepository {

    int findByNoticeCount();

    List<BoardListDTO> findByNoticeList(@Param("offset") int offset,@Param("limit") int limit);
}
