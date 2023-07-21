package com.feed.feedsoup.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplyRepository {
    void saveReply(@Param("boardNo")int boardNo, @Param("reply") String reply, @Param("memberNo") int memberNo);

    int deleteReply(@Param("boardNo")int boardNo,@Param("replyNo") int replyNo);
}
