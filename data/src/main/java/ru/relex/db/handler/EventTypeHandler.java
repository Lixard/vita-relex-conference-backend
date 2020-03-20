package ru.relex.db.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import ru.relex.commons.model.EventType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedTypes(EventType.class)
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT})
public class EventTypeHandler implements TypeHandler<EventType> {
    @Override
    public void setParameter(final PreparedStatement ps, final int i,
                             final EventType parameter, final JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setInt(i, parameter.getId());
        } else {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public EventType getResult(final ResultSet rs, final String columnName) throws SQLException {
        return EventType.of(rs.getInt(columnName)).orElse(null);
    }

    @Override
    public EventType getResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return EventType.of(rs.getInt(columnIndex)).orElse(null);
    }

    @Override
    public EventType getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return EventType.of(cs.getInt(columnIndex)).orElse(null);
    }
}
