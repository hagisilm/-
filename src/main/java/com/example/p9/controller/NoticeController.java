package com.example.p9.controller;

import com.example.p9.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class NoticeController {

    private final NoticeService noticeService;


    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String noticePage(Model model){
        var notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "notice-list";
    }
    @GetMapping("/notice/{id}")
    public String notice(@PathVariable UUID id, Model model) {
        model.addAttribute("notice", noticeService.getNoticesById(id));
        return "detail";
    }


    @GetMapping("/new-notice")
    public String newNoticePage(){
        return "new-notice";
    }

    @PostMapping("/notices")
    public String newNotice(CreateNoticeRequest createNoticeRequest){
        noticeService.createNotice(
                createNoticeRequest.title(),createNoticeRequest.content()
        );
        return "redirect:/notices";
    }
}
