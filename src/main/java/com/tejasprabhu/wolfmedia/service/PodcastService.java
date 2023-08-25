package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.PodcastDAO;
import com.tejasprabhu.wolfmedia.model.Podcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PodcastService {

    private static final Logger logger = LoggerFactory.getLogger(PodcastService.class);

    @Autowired
    private PodcastDAO podcastDAO;

    public List<Podcast> findWithFilters(Map<String, Object> filters) {
        logger.info("Fetching podcasts with filters: {}", filters);
        return podcastDAO.findWithFilters(filters);
    }

    @Transactional
    public Podcast save(Podcast podcast) {
        logger.info("Saving podcast: {}", podcast);
        podcastDAO.save(podcast);
        return podcast;
    }

    @Transactional
    public Podcast update(Podcast podcast) {
        logger.info("Updating podcast with ID {}: {}", podcast.getPodcastID(), podcast);
        podcastDAO.update(podcast);
        return podcast;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting podcast with ID: {}", id);
        podcastDAO.delete(id);
    }
}
