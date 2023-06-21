package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmailConfirmRepository {
    void save(@Param("emailConfirm") EmailConfirmDTO emailConfirm);

    void update(@Param("emailConfirmDTO")EmailConfirmDTO emailConfirmDTO);

    EmailConfirmDTO findByValidKey(@Param("emailConfirmDTO")EmailConfirmDTO emailConfirmDTO);

    EmailConfirmDTO findByValidNum(@Param("emailConfirmDTO")EmailConfirmDTO emailConfirmDTO);
}
