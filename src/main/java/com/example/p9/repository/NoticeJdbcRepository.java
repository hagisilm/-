package com.example.p9.repository;

import com.example.p9.model.Notice;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class NoticeJdbcRepository implements NoticeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public NoticeJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Notice> findAll() {
        return jdbcTemplate.query("SELECT * FROM notices", noticeRowMapper);
    }

    @Override
    public Notice insert(Notice notice) {
        var update = jdbcTemplate.update("INSERT INTO notices(notice_id, title, content, created_at, updated_at)" +
                " VALUES (UUID_TO_BIN(:noticeId), :title, :content, :createdAt, :updatedAt)", toParamMap(notice));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return notice;
    }

    @Override
    public Optional<Notice> findById(UUID noticeId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM notices WHERE product_id = UUID_TO_BIN(:noticeId)",
                            Collections.singletonMap("noticeId", noticeId.toString().getBytes()), noticeRowMapper)
            );
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {

    }



    private static final RowMapper<Notice> noticeRowMapper = (resultSet, i) -> {
        var noticeId = toUUID(resultSet.getBytes("notice_id"));
        var title = resultSet.getString("title");
        var content = resultSet.getString("content");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new Notice(noticeId, title, content, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Notice notice) {
        var paraMap = new HashMap<String, Object>();
        paraMap.put("noticeId",notice.getNoticeId().toString().getBytes());
        paraMap.put("title",notice.getTitle());
        paraMap.put("content",notice.getContent());
        paraMap.put("createdAt",notice.getCreatedAt());
        paraMap.put("updatedAt",notice.getUpdatedAt());
        return paraMap;
    }

    public static UUID toUUID(byte[] bytes) {
        var byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(),byteBuffer.getLong());
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
