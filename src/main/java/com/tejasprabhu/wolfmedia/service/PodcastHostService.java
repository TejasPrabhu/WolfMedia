package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.PodcastHostDAO;
import com.tejasprabhu.wolfmedia.model.PodcastHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PodcastHostService {

    private static final Logger logger = LoggerFactory.getLogger(PodcastHostService.class);

    @Autowired
    private PodcastHostDAO podcastHostDAO;

    public List<PodcastHost> findWithFilters(Map<String, Object> filters) {
        logger.info("Fetching podcast hosts with filters: {}", filters);
        return podcastHostDAO.findWithFilters(filters);
    }

    @Transactional
    public PodcastHost save(PodcastHost podcastHost) {
        logger.info("Saving podcast host: {}", podcastHost);
        podcastHostDAO.save(podcastHost);
        return podcastHost;
    }

    @Transactional
    public PodcastHost update(PodcastHost podcastHost) {
        logger.info("Updating podcast host with ID {}: {}", podcastHost.getHostID(), podcastHost);
        podcastHostDAO.update(podcastHost);
        return podcastHost;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting podcast host with ID: {}", id);
        podcastHostDAO.delete(id);
    }
}
