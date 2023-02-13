package com.example.p9.controller;

import com.example.p9.model.Notice;
import com.example.p9.service.NoticeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeRestController {
    private final NoticeService noticeService;

    public NoticeRestController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("api/v1/notices")
    public List<Notice> noticeList(){
        return noticeService.getAllNotices();
    }

}
