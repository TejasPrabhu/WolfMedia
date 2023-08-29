// SongDAO.java
package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.Song;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAO extends GenericDAO<Song> {

    private static final String SELECT_BATCH_OF_SONGS = "SELECT * FROM Song ORDER BY SongID LIMIT :limit OFFSET :offset";

    @Override
    protected String getTableName() {
        return "Song";
    }

    @Override
    protected RowMapper<Song> getRowMapper() {
        return (resultSet, rowNum) -> new Song(
                resultSet.getInt("SongID"),
                resultSet.getString("Title"),
                resultSet.getInt("ArtistID"),
                resultSet.getTime("Duration"),
                resultSet.getString("Genres"),
                resultSet.getInt("AlbumID"),
                resultSet.getInt("PlayCount"),
                resultSet.getDate("ReleaseDate"),
                resultSet.getString("ReleaseCountry"),
                resultSet.getString("Language"),
                resultSet.getDouble("RoyaltyRate")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "SongID";
    }

    @Override
    protected Object getIdValue(Song song) {
        return song.getSongID();
    }

    public List<Song> getBatchOfSongs(int batchSize, int offset) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", batchSize);
        params.addValue("offset", offset);
        return jdbcTemplate.query(SELECT_BATCH_OF_SONGS, (PreparedStatementSetter) params, getRowMapper());
    }

    public Song getSong(int songID) {
        String query = "SELECT * FROM Song WHERE SongID = ?";
        List<Song> songs = jdbcTemplate.query(query, new Object[]{songID}, getRowMapper());
        return songs.isEmpty() ? null : songs.get(0);
    }

    public int getLabelId(int songID) {
        String query = "SELECT a.LabelID " +
                "FROM Song s " +
                "JOIN Artist a ON s.ArtistID = a.ArtistID " +
                "WHERE s.SongID = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{songID}, Integer.class);
    }



}
