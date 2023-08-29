package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.ArtistPayment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistPaymentDAO extends GenericDAO<ArtistPayment> {

    @Override
    protected String getTableName() {
        return "ArtistPayments";
    }

    @Override
    protected RowMapper<ArtistPayment> getRowMapper() {
        return (resultSet, rowNum) -> new ArtistPayment(
                resultSet.getInt("paymentID"),
                resultSet.getInt("artistID"),
                resultSet.getInt("songID"),
                resultSet.getBigDecimal("paymentAmount"),
                resultSet.getDate("paymentDate")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "paymentID";
    }

    @Override
    protected Object getIdValue(ArtistPayment entity) {
        return entity.getPaymentID();
    }
}
