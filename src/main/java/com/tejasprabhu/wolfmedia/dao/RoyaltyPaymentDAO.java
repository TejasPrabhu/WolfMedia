package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.RoyaltyPayment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoyaltyPaymentDAO extends GenericDAO<RoyaltyPayment> {

    @Override
    protected String getTableName() {
        return "RoyaltyPayments";
    }

    @Override
    protected RowMapper<RoyaltyPayment> getRowMapper() {
        return (resultSet, rowNum) -> new RoyaltyPayment(
                resultSet.getInt("royaltyPaymentID"),
                resultSet.getInt("songID"),
                resultSet.getDate("paymentDate"),
                resultSet.getBigDecimal("calculatedAmount"),
                resultSet.getString("paymentStatus")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "royaltyPaymentID";
    }

    @Override
    protected Object getIdValue(RoyaltyPayment entity) {
        return entity.getRoyaltyPaymentID();
    }


    public List<RoyaltyPayment> getPaymentsForCurrentMonth(int batchSize, int offset) {
        String query = "SELECT * FROM " + getTableName() +
                " WHERE EXTRACT(MONTH FROM paymentDate) = EXTRACT(MONTH FROM CURRENT_DATE)" +
                " AND EXTRACT(YEAR FROM paymentDate) = EXTRACT(YEAR FROM CURRENT_DATE)" +
                " LIMIT ? OFFSET ?";

        return jdbcTemplate.query(query, new Object[]{batchSize, offset}, getRowMapper());
    }

}
