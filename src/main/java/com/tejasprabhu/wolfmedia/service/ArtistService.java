package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.ArtistDAO;
import com.tejasprabhu.wolfmedia.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ArtistService {

    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);

    @Autowired
    private ArtistDAO artistDAO;

    public List<Artist> findWithFilters(Map<String, Object> filters) {
        logger.info("Fetching artists with filters: {}", filters);
        return artistDAO.findWithFilters(filters);
    }

    @Transactional
    public Artist save(Artist artist) {
        logger.info("Saving artist: {}", artist);
        artistDAO.save(artist);
        return artist;
    }

    @Transactional
    public Artist update(Artist artist) {
        logger.info("Updating artist with ID {}: {}", artist.getArtistID(), artist);
        artistDAO.update(artist);
        return artist;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting artist with ID: {}", id);
        artistDAO.delete(id);
    }
}
