package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.Podcast;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PodcastDAO extends GenericDAO<Podcast> {

    @Override
    protected String getTableName() {
        return "Podcast";
    }

    @Override
    protected RowMapper<Podcast> getRowMapper() {
        return (resultSet, rowNum) -> new Podcast(
                resultSet.getInt("PodcastID"),
                resultSet.getInt("HostID"),
                resultSet.getString("Title"),
                resultSet.getDate("ReleaseDate")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "PodcastID";
    }

    @Override
    protected Object getIdValue(Podcast podcast) {
        return podcast.getPodcastID();
    }
}
