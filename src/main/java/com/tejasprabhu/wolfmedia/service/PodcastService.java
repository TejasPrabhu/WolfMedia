package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.PodcastDAO;
import com.tejasprabhu.wolfmedia.model.Podcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PodcastService {

    private static final Logger logger = LoggerFactory.getLogger(PodcastService.class);

    @Autowired
    private PodcastDAO podcastDAO;

//    public List<Podcast> findWithFilters(Map<String, Object> filters) {
//        logger.info("Fetching podcasts with filters: {}", filters);
//        return podcastDAO.findWithFilters(filters);
//    }

    public List<Podcast> findWithFilters(Map<String, Object> filters){
        Map<String, Object> castedFilters = new HashMap<>();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ("hostID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if  ("podcastID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("releaseDate".equals(key)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed;
                try {
                    parsed = format.parse(value.toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                value = new java.sql.Date(parsed.getTime());
                castedFilters.put(key, value);
            }
        }
        return podcastDAO.findWithFilters(castedFilters);
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
