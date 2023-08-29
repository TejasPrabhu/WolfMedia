package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<User> {

    @Override
    protected String getTableName() {
        return "ServiceUser";
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return (resultSet, rowNum) -> new User(
                resultSet.getInt("UserID"),
                resultSet.getString("UserName"),
                resultSet.getString("Email"),
                resultSet.getString("Password"),
                resultSet.getString("SubscriptionType"),
                resultSet.getString("SubscriptionStatus"),
                resultSet.getString("City")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "UserID";
    }

    @Override
    protected Object getIdValue(User user) {
        return user.getUserID();
    }
}
