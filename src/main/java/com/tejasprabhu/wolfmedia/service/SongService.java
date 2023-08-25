// SongService.java
package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.SongDAO;
import com.tejasprabhu.wolfmedia.model.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SongService {

    private static final Logger logger = LoggerFactory.getLogger(SongService.class);

    @Autowired
    private SongDAO songDAO;

    public List<Song> findWithFilters(Map<String, Object> filters) {
        logger.info("Fetching songs with filters: {}", filters);
        return songDAO.findWithFilters(filters);
    }

    @Transactional
    public Song save(Song song) {
        logger.info("Saving song: {}", song);
        songDAO.save(song);
        return song;
    }

    @Transactional
    public Song update(Song song) {
        logger.info("Updating song with ID {}: {}", song.getSongID(), song);
        songDAO.update(song);
        return song;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting song with ID: {}", id);
        songDAO.delete(id);
    }
}
