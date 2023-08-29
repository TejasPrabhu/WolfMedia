package com.tejasprabhu.wolfmedia.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GenericDAO<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    // This abstract method should be implemented by specific DAOs to provide a RowMapper for the type.
    protected abstract RowMapper<T> getRowMapper();

    protected abstract String getIdColumnName();

    protected abstract Object getIdValue(T entity);

    // Get the table name dynamically. This will be overridden by the specific DAO.
    protected abstract String getTableName();

// GenericDAO.java

    public List<T> findWithFilters(Map<String, Object> filters) {
        return findWithFilters(filters, null, null);
    }

    public List<T> findWithFilters(Map<String, Object> filters, Integer limit, Integer offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + getTableName() + " WHERE 1=1");
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            sql.append(" AND ").append(entry.getKey()).append(" = ?");
            params.add(entry.getValue());
        }
//        for (Map.Entry<String, Object> entry : filters.entrySet()) {
//            Object value = entry.getValue();
//            if (value instanceof String && isIntegerColumn(entry.getKey())) {
//                value = Integer.valueOf((String) value);
//            }
//            sql.append(" AND ").append(entry.getKey()).append(" = ?");
//            params.add(value);
//        }

        if (limit != null) {
            sql.append(" LIMIT ?");
            params.add(limit);
        }

        if (offset != null) {
            sql.append(" OFFSET ?");
            params.add(offset);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), getRowMapper());
    }



    public int save(T entity) {
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Allows us to access private fields
            try {
                Object value = field.get(entity);
                if (value != null) {
                    columns.append(field.getName()).append(", ");
                    placeholders.append("?, ");
                    params.add(value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Remove trailing comma and space
        columns.setLength(columns.length() - 2);
        placeholders.setLength(placeholders.length() - 2);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", getTableName(), columns, placeholders);
        return jdbcTemplate.update(sql, params.toArray());
    }

    public int update(T entity) {
        StringBuilder setters = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (value != null) {
                    setters.append(field.getName()).append(" = ?, ");
                    params.add(value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Remove trailing comma and space
        setters.setLength(setters.length() - 2);

        // Add the ID value for the WHERE clause
        params.add(getIdValue(entity));

        String sql = String.format("UPDATE %s SET %s WHERE %s = ?", getTableName(), setters, getIdColumnName());
        return jdbcTemplate.update(sql, params.toArray());
    }

    public int delete(Object id) {
        String sql = String.format("DELETE FROM %s WHERE %s = ?", getTableName(), getIdColumnName());
        return jdbcTemplate.update(sql, id);
    }
}
