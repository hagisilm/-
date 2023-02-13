package com.example.p9.service;

import com.example.p9.model.Notice;
import com.example.p9.model.NoticeResponseDto;
import com.example.p9.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultNoticeService implements NoticeService{

    private final NoticeRepository noticeRepository;

    public DefaultNoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    @Override
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }


    @Override
    public Notice createNotice(String title, String content) {
        var notice = new Notice(UUID.randomUUID(), title, content, LocalDateTime.now(),LocalDateTime.now());
        return noticeRepository.insert(notice);
    }

}
