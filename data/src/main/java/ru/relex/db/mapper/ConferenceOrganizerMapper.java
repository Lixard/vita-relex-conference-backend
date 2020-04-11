package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.ConferenceOrganizer;

import java.util.List;

@Mapper
public interface ConferenceOrganizerMapper {

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT 1 FROM conference_organizers " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId} AND NOT deleted)"
    )
    boolean isOrganizerRightsExists(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT 1 FROM conference_organizers WHERE user_id = #{userId} AND conference_id = #{conferenceId})"
    )
    boolean isExists(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT co.user_id, co.conference_id, co.created_by, co.created_at, co.deleted " +
            "FROM conference_organizers co " +
            "JOIN users u ON co.user_id = u.user_id " +
            "JOIN conferences c ON co.conference_id = c.conference_id " +
            "WHERE NOT (co.deleted OR u.deleted OR c.deleted) " +
            "AND co.user_id = #{userId}"
    )
    List<ConferenceOrganizer> getConferencesByOrganizerId(@Param("userId") int userId);

    @Select(
            //language=PostgreSQL
            "SELECT co.user_id, co.conference_id, co.created_by, co.created_at, co.deleted " +
            "FROM conference_organizers co " +
            "JOIN users u ON co.user_id = u.user_id " +
            "JOIN conferences c ON co.conference_id = c.conference_id " +
            "WHERE NOT (co.deleted OR u.deleted OR c.deleted) " +
            "AND co.conference_id = #{conferenceId}"
    )
    List <ConferenceOrganizer> getOrganizersByConferenceId(@Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT co.user_id, co.conference_id, co.created_by, co.created_at, co.deleted " +
            "FROM conference_organizers co " +
            "JOIN users u ON co.user_id = u.user_id " +
            "JOIN conferences c ON co.conference_id = c.conference_id " +
            "WHERE NOT (co.deleted OR u.deleted OR c.deleted) " +
            "AND co.conference_id = #{conferenceId} AND co.user_id = #{userId}"
    )
    ConferenceOrganizer findById(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers " +
            "WHERE deleted "+
            "AND user_id = #{userId} AND conference_id = #{conferenceId}"
    )
    ConferenceOrganizer findDeletedById(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId}"
    )
    void delete(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO conference_organizers(user_id, conference_id, " +
            "created_by, created_at) " +
            "VALUES (#{userId}, #{conferenceId}, #{createdBy}, current_timestamp)"
    )
    void insert(ConferenceOrganizer conferenceOrganizer);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET created_at = current_timestamp, created_by = #{createdBy} " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId}"
    )
    void update(ConferenceOrganizer conferenceOrganizer);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'false' " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId}"
    )
    void resurrect(@Param("userId") int userId, @Param("conferenceId") int conferenceId);
}
