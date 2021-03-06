package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.Conference;
import ru.relex.db.model.User;

import java.util.List;

@Mapper
public interface ConferenceMapper {


    @Select(
            //language=PostgreSQL
            "SELECT conference_id," +
                    " conference_name," +
                    " html_description," +
                    " short_description," +
                    " link_image," +
                    " location," +
                    " date_start," +
                    " date_end," +
                    " owner," +
                    " created_at," +
                    " deleted" +
                    " FROM conferences WHERE owner = #{userId}"
    )
    List<Conference> getConferencesWhereUserIsOwner(int userId);

    @Select(
            //language=PostgreSQL
            "SELECT u.user_id, u.username, u.first_name, u.last_name, u.email, u.role, u.link_image FROM conferences c " +
            "JOIN users u ON c.owner = u.user_id " +
            "WHERE c.conference_id = #{conferenceId} AND u.user_id = c.owner"
    )
    User getConferenceOwner(@Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT 1 FROM conferences " +
            "WHERE owner = #{userId} AND conference_id = #{conferenceId})"
    )
    boolean isOwnerRightsExists(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT 1 FROM conferences WHERE conference_id = #{id} AND NOT deleted)"
    )
    boolean isConferenceExists(@Param("id") int id);

    @Select(
            //language=PostgreSQL
            "SELECT " +
            "conference_id, " +
            "conference_name, " +
            "html_description, " +
            "short_description, " +
            "link_image, " +
            "location, " +
            "date_start, " +
            "date_end, " +
            "owner,  " +
            "created_at, " +
            "deleted " +
            "FROM conferences WHERE NOT deleted"
    )
    List<Conference> getConferences();

    @Select(
            //language=PostgreSQL
            "SELECT " +
            "conference_id, " +
            "conference_name, " +
            "html_description, " +
            "short_description, " +
            "link_image, " +
            "location, " +
            "date_start, " +
            "date_end, " +
            "owner, " +
            "created_at, " +
            "deleted " +
            "FROM conferences " +
            "WHERE conference_id = #{id} AND NOT deleted"
    )
    Conference findById(@Param("id") int id);

    @Update(
            //language=PostgreSQL
            "UPDATE conferences SET " +
            "conference_name = #{conferenceName}, " +
            "html_description = #{htmlDescription}, " +
            "short_description = #{shortDescription}, " +
            "link_image = #{linkImage}, " +
            "location = #{location}, " +
            "date_start = #{dateStart}, " +
            "date_end = #{dateEnd} " +
            "WHERE conference_id = #{conferenceId} AND NOT deleted"
    )
    void update(Conference conference);


    @Update(
            //language=PostgreSQL
            "UPDATE conferences SET deleted = 'true' WHERE conference_id = #{id}"
    )
    void delete(@Param("id") int id);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO conferences(conference_name, html_description, short_description, link_image, location," +
            " date_start, date_end, owner, created_at) " +
            "VALUES (#{conferenceName}, #{htmlDescription}, #{shortDescription}, #{linkImage}, #{location}, #{dateStart}, " +
            "#{dateEnd}, #{owner}, current_timestamp)"
    )
    @SelectKey(
            before = false,
            keyProperty = "conferenceId",
            keyColumn = "conference_id",
            statement = "select currval('conferences_conference_id_seq')",
            resultType = Integer.class
    )
    void insert(Conference conference);

    @Update(
            //language=PostgreSQL
            "UPDATE conferences SET deleted = 'false' WHERE conference_id = #{id}"
    )
    void resurrect(@Param("id") int id);
}
