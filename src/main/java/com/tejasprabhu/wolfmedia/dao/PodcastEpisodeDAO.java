package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.PodcastEpisode;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PodcastEpisodeDAO extends GenericDAO<PodcastEpisode> {

    @Override
    protected String getTableName() {
        return "PodcastEpisode";
    }

    @Override
    protected RowMapper<PodcastEpisode> getRowMapper() {
        return (resultSet, rowNum) -> new PodcastEpisode(
                resultSet.getInt("EpisodeID"),
                resultSet.getInt("PodcastID"),
                resultSet.getString("Title"),
                resultSet.getTime("Duration"),
                resultSet.getInt("ListeningCount"),
                resultSet.getInt("AdCount"),
                resultSet.getBigDecimal("FlatFee")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "EpisodeID";
    }

    @Override
    protected Object getIdValue(PodcastEpisode podcastEpisode) {
        return podcastEpisode.getEpisodeID();
    }
}
