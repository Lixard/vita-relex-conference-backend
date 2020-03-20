package ru.relex.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.relex.db.model.ConferenceOrganizer;
import ru.relex.db.model.EventSpeaker;

import java.util.List;

public interface EventSpeakerMapper {
    @Select("SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers WHERE NOT deleted ")
    List<ConferenceOrganizer> getConferenceOrganizers();

    @Select("SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers WHERE NOT deleted" +
            "WHERE user_id = #{userId}")
    ConferenceOrganizer findByUserId(@Param("userId") int userId);

    @Select("SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers WHERE NOT deleted" +
            "WHERE event_id = #{eventId}")
    ConferenceOrganizer findByConferenceId(@Param("eventId") int eventId);

    @Select("SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers " +
            "WHERE user_id = #{userId} AND event_id = #{eventId} AND NOT deleted ")
    ConferenceOrganizer findById(@Param("userId") int userId, @Param("eventId") int eventId);

    @Update("UPDATE event_speakers SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}")
    void delete(@Param("userId") int userId, @Param("eventId") int eventId);

    @Insert("INSERT INTO event_speakers(user_id, event_id, " +
            "created_by, created_at, deleted) " +
            "VALUES (#{userId}, #{eventId}, #{createdBy}, #{createdAt}, #{deleted})")
    void insert(EventSpeaker eventSpeaker);
}
