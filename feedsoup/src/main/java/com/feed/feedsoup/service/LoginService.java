package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.LoginFormDTO;
import com.feed.feedsoup.dto.MemberDTO;
import com.feed.feedsoup.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public MemberDTO login(LoginFormDTO loginFormDTO) {
        return loginRepository.login(loginFormDTO);
    }

}
