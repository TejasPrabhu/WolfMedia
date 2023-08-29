package com.tejasprabhu.wolfmedia.dao;

import com.tejasprabhu.wolfmedia.model.RecordLabel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecordLabelDAO extends GenericDAO<RecordLabel> {

    @Override
    protected String getTableName() {
        return "RecordLabel";
    }

    @Override
    protected RowMapper<RecordLabel> getRowMapper() {
        return (resultSet, rowNum) -> new RecordLabel(
                resultSet.getInt("LabelID"),
                resultSet.getString("LabelName")
        );
    }

    @Override
    protected String getIdColumnName() {
        return "LabelID";
    }

    @Override
    protected Object getIdValue(RecordLabel recordLabel) {
        return recordLabel.getLabelID();
    }
}
