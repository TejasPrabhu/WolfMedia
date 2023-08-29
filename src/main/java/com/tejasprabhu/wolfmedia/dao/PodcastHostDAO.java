package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.PodcastHost;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PodcastHostDAO extends GenericDAO<PodcastHost> {

    @Override
    protected String getTableName() {
        return "PodcastHost";
    }

    @Override
    protected RowMapper<PodcastHost> getRowMapper() {
        return (resultSet, rowNum) -> new PodcastHost(
                resultSet.getInt("HostID"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName"),
                resultSet.getString("Email"),
                resultSet.getString("Phone"),
                resultSet.getString("City")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "HostID";
    }

    @Override
    protected Object getIdValue(PodcastHost podcastHost) {
        return podcastHost.getHostID();
    }
}
