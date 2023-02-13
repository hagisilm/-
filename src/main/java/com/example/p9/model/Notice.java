package com.example.p9.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notice {
    private final UUID noticeId;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public Notice(UUID noticeId, String title, String content) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }
    public Notice(UUID noticeId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public UUID getNoticeId() {
        return noticeId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
