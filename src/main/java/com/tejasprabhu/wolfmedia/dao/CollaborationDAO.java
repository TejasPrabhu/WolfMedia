package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.Collaboration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class CollaborationDAO extends GenericDAO<Collaboration> {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    protected String getTableName() {
        return "Collaboration";
    }

    @Override
    protected RowMapper<Collaboration> getRowMapper() {
        return (resultSet, rowNum) -> new Collaboration(
                resultSet.getInt("CollaborationID"),
                resultSet.getInt("SongID"),
                resultSet.getInt("ArtistID")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "CollaborationID";
    }

    @Override
    protected Object getIdValue(Collaboration collaboration) {
        return collaboration.getCollaborationID();
    }


    public List<Collaboration> getCollaboratorsForSong(int songID) {
        String query = "SELECT * FROM " + getTableName() + " WHERE SongID = :songID";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("songID", songID);

        return namedParameterJdbcTemplate.query(query, namedParameters, new BeanPropertyRowMapper<>(Collaboration.class));
    }

}
