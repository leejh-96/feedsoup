package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.RegisterFormDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RegisterRepository {
    void save(RegisterFormDTO register);
}
