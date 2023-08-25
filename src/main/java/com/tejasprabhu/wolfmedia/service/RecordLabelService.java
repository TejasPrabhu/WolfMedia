package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.RecordLabelDAO;
import com.tejasprabhu.wolfmedia.model.RecordLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RecordLabelService {

    private static final Logger logger = LoggerFactory.getLogger(RecordLabelService.class);

    @Autowired
    private RecordLabelDAO recordLabelDAO;

    public List<RecordLabel> findWithFilters(Map<String, Object> filters) {
        logger.info("Fetching record labels with filters: {}", filters);
        return recordLabelDAO.findWithFilters(filters);
    }

    @Transactional
    public RecordLabel save(RecordLabel recordLabel) {
        logger.info("Saving record label: {}", recordLabel);
        recordLabelDAO.save(recordLabel);
        return recordLabel;
    }

    @Transactional
    public RecordLabel update(RecordLabel recordLabel) {
        logger.info("Updating record label with ID {}: {}", recordLabel.getLabelID(), recordLabel);
        recordLabelDAO.update(recordLabel);
        return recordLabel;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting record label with ID: {}", id);
        recordLabelDAO.delete(id);
    }
}
