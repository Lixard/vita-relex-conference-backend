package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.Conference;

import java.util.List;

@Mapper
public interface ConferenceMapper {
    @Select("SELECT " +
            "conference_id, " +
            "conference_name, " +
            "html_description, " +
            "location, " +
            "date_start, " +
            "date_end, " +
            "owner,  " +
            "created_at, " +
            "deleted" +
            "FROM conferences WHERE NOT deleted"
    )
    List<Conference> getConferences();

    @Select("SELECT " +
            "conference_id, " +
            "conference_name, " +
            "html_description, " +
            "location, " +
            "date_start, " +
            "date_end, " +
            "owner, " +
            "created_at, " +
            "deleted" +
            "FROM conferences " +
            "WHERE conference_id = #{id} AND NOT deleted"
    )
    Conference findById(@Param("id") int id);

    @Update("UPDATE conferences SET " +
            "conference_name = #{conferenceName}, " +
            "html_description = #{htmlDescription}, " +
            "location = #{location}, " +
            "date_start = #{dateStart}, " +
            "date_end = #{dateEnd}, " +
            "owner = #{owner}, " +
            "created_at = #{createdAt}, " +
            "deleted = #{deleted} " +
            "WHERE conference_id = #{conferenceId}"
    )
    void update(Conference conference);

    @Delete("DELETE FROM conferences WHERE conference_id = #{id}")
    void delete(@Param("id") int id);

    @Insert("INSERT INTO conferences(conference_name, html_description, location," +
            " date_start, date_end, owner, created_at, deleted) " +
            "VALUES (#{conferenceName}, #{htmlDescription}, #{location}, #{dateStart}, " +
            "#{dateEnd}, #{owner}, #{createdAt}, #{deleted}))"
    )
    @SelectKey(
            before = false,
            keyProperty = "conferenceId",
            keyColumn = "conference_id",
            statement = "select currval('conferences_conference_id_seq')",
            resultType = Integer.class
    )
    void insert(Conference conference);
}
