package com.example.p9.service;

import com.example.p9.model.Notice;
import org.springframework.data.relational.core.sql.Not;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoticeService {

    List<Notice> getAllNotices();



    Notice createNotice(String title, String content);



}
