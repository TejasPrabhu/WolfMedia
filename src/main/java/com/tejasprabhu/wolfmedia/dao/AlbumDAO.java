package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.Album;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumDAO extends GenericDAO<Album> {

    @Override
    protected String getTableName() {
        return "Album";
    }

    @Override
    protected RowMapper<Album> getRowMapper() {
        return (resultSet, rowNum) -> new Album(
                resultSet.getInt("AlbumID"),
                resultSet.getString("AlbumName"),
                resultSet.getInt("ArtistID"),
                resultSet.getDate("ReleaseYear"),
                resultSet.getString("Edition")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "AlbumID";
    }

    @Override
    protected Object getIdValue(Album album) {
        return album.getAlbumID();
    }
}
