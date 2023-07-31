package com.feed.feedsoup.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

    NOTICE_LIST("noticeList", 10, 110000),
    NOTICE_COUNT("noticeCount",10,110000);

    private final String cacheName;//캐쉬이름
    private final int expiredAfterWrite;//만료시간
    private final int maximumSize;//저장가능한 최대 갯수

}
