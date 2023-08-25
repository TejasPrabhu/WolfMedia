package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.PodcastEpisodeDAO;
import com.tejasprabhu.wolfmedia.model.PodcastEpisode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        logger.info("Fetching podcast episodes with filters: {}", filters);
        return podcastEpisodeDAO.findWithFilters(filters);
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
