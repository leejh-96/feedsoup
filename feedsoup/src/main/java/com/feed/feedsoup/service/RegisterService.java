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
    public void registerSave(RegisterFormDTO register) {

        registerRepository.save(register);
    }
}
