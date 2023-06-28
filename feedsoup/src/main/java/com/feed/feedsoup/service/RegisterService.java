package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.RegisterFormDTO;
import com.feed.feedsoup.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository registerRepository;

    @Transactional
    public void save(RegisterFormDTO registerFormDTO) {
        registerRepository.save(registerFormDTO);
    }
    public boolean duplicateNickname(String memberNickname) {
        return registerRepository.duplicateNickname(memberNickname) != 0 ? false : true;
    }
}
