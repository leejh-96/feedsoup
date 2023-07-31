package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.BoardListDTO;
import com.feed.feedsoup.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Cacheable(cacheNames = "noticeCount")
    public int findByNoticeCount() {
        return noticeRepository.findByNoticeCount();
    }

    @Cacheable(cacheNames = "noticeList")
    public List<BoardListDTO> findByNoticeList(int offset, int limit) {
        return noticeRepository.findByNoticeList(offset,limit);
    }


}
