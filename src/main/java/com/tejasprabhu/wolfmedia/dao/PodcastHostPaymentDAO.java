package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.PodcastHostPayment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PodcastHostPaymentDAO extends GenericDAO<PodcastHostPayment> {

    @Override
    protected String getTableName() {
        return "PodcastHostPayments";
    }

    @Override
    protected RowMapper<PodcastHostPayment> getRowMapper() {
        return (resultSet, rowNum) -> new PodcastHostPayment(
                resultSet.getInt("paymentID"),
                resultSet.getInt("hostID"),
                resultSet.getInt("podcastID"),
                resultSet.getInt("episodeID"),
                resultSet.getBigDecimal("paymentAmount"),
                resultSet.getDate("paymentDate")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "paymentID";
    }

    @Override
    protected Object getIdValue(PodcastHostPayment entity) {
        return entity.getPaymentID();
    }
}
