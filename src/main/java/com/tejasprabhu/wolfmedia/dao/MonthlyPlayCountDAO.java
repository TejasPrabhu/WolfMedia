package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.MonthlyPlayCount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MonthlyPlayCountDAO extends GenericDAO<MonthlyPlayCount> {

    @Override
    protected String getTableName() {
        return "MonthlyPlayCount";
    }

    @Override
    protected RowMapper<MonthlyPlayCount> getRowMapper() {
        return (resultSet, rowNum) -> new MonthlyPlayCount(
                resultSet.getInt("ID"),
                resultSet.getInt("SongID"),
                resultSet.getInt("Year"),
                resultSet.getInt("Month"),
                resultSet.getInt("PlayCount")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "ID";
    }

    @Override
    protected Object getIdValue(MonthlyPlayCount monthlyPlayCount) {
        return monthlyPlayCount.getPlaycountID();
    }

    public MonthlyPlayCount getPlayCountForSongInMonth(int songID, int year, int month) {
        String sql = "SELECT * FROM MonthlyPlayCount WHERE SongID = ? AND Year = ? AND Month = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{songID, year, month}, getRowMapper());
    }
}
