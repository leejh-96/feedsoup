package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.RegisterFormDTO;
import com.feed.feedsoup.repository.RegisterRepository;
import com.feed.feedsoup.util.PasswordEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository registerRepository;

    public void save(RegisterFormDTO registerFormDTO) {

        String encryptPassword = PasswordEncryption.encryptPassword(registerFormDTO.getMemberPassword());
        registerFormDTO.setMemberPassword(encryptPassword);

        registerRepository.save(registerFormDTO);
    }
    public boolean duplicateNickname(String memberNickname) {
        return registerRepository.duplicateNickname(memberNickname) != 0 ? false : true;
    }
}
