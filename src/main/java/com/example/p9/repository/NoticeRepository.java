package com.example.p9.repository;

import com.example.p9.model.Notice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoticeRepository {

    List<Notice> findAll();

    Notice insert(Notice notice);

    Optional<Notice> findById(UUID productId);

    void deleteAll();


}
