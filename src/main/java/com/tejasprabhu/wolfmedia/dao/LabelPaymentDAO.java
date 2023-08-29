package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.LabelPayment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LabelPaymentDAO extends GenericDAO<LabelPayment> {

    @Override
    protected String getTableName() {
        return "LabelPayments";
    }

    @Override
    protected RowMapper<LabelPayment> getRowMapper() {
        return (resultSet, rowNum) -> new LabelPayment(
                resultSet.getInt("paymentID"),
                resultSet.getInt("labelID"),
                resultSet.getInt("artistID"),
                resultSet.getBigDecimal("paymentAmount"),
                resultSet.getDate("paymentDate")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "paymentID";
    }

    @Override
    protected Object getIdValue(LabelPayment entity) {
        return entity.getPaymentID();
    }
}
