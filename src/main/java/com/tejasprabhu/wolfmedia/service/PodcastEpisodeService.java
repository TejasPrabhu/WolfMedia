package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.PodcastEpisodeDAO;
import com.tejasprabhu.wolfmedia.model.PodcastEpisode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PodcastEpisodeService {

    private static final Logger logger = LoggerFactory.getLogger(PodcastEpisodeService.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PodcastEpisodeDAO podcastEpisodeDAO;

    public List<PodcastEpisode> findWithFilters(Map<String, Object> filters) {
        Map<String, Object> castedFilters = new HashMap<>();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ("episodeID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("podcastID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("listeningCount".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("adCount".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("flatFee".equals(key)) {
                castedFilters.put(key, Double.valueOf(value.toString()));
            } else if ("duration".equals(key)) {
                castedFilters.put(key, Time.valueOf(value.toString()));
            }
        }
        logger.info("Fetching podcast episodes with filters: {}", castedFilters);
        return podcastEpisodeDAO.findWithFilters(castedFilters);
    }

    @Transactional
    public PodcastEpisode save(PodcastEpisode podcastEpisode) {
        logger.info("Saving podcast episode: {}", podcastEpisode);
        podcastEpisodeDAO.save(podcastEpisode);
        logger.info("Making payment for podcast episode: {}", podcastEpisode);
        paymentService.distributePodcastPayments(podcastEpisode);
        return podcastEpisode;
    }

    @Transactional
    public PodcastEpisode update(PodcastEpisode podcastEpisode) {
        logger.info("Updating podcast episode with ID {}: {}", podcastEpisode.getEpisodeID(), podcastEpisode);
        podcastEpisodeDAO.update(podcastEpisode);
        return podcastEpisode;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting podcast episode with ID: {}", id);
        podcastEpisodeDAO.delete(id);
    }
}
