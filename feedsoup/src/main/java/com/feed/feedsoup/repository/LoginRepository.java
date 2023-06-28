package com.feed.feedsoup.repository;

import com.feed.feedsoup.dto.LoginFormDTO;
import com.feed.feedsoup.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginRepository {

    MemberDTO login(LoginFormDTO loginFormDTO);
}
