package com.feed.feedsoup.service;

import com.feed.feedsoup.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void saveReply(int boardNo, String reply, int memberNo) {
        replyRepository.saveReply(boardNo,reply,memberNo);
    }

    public boolean deleteReply(int boardNo, int replyNo) {
        return replyRepository.deleteReply(boardNo, replyNo) > 0 ? true : false;
    }
}
