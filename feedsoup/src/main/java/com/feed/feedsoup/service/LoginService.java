package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.LoginFormDTO;
import com.feed.feedsoup.dto.LoginMemberDTO;
import com.feed.feedsoup.dto.MemberDTO;
import com.feed.feedsoup.repository.LoginRepository;
import com.feed.feedsoup.util.PasswordEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public MemberDTO getMemberInfo(LoginFormDTO loginFormDTO) {
        return loginRepository.login(loginFormDTO);
    }

}
