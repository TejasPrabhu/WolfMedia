// SongService.java
package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.SongDAO;
import com.tejasprabhu.wolfmedia.model.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongService {

    private static final Logger logger = LoggerFactory.getLogger(SongService.class);

    @Autowired
    private SongDAO songDAO;

    public List<Song> findWithFilters(Map<String, Object> filters) {
        Map<String, Object> castedFilters = new HashMap<>();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ("songID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("artistID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("albumID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("playCount".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            } else if ("royaltyRate".equals(key)) {
                castedFilters.put(key, Double.valueOf(value.toString()));
            } else if ("duration".equals(key)) {
                castedFilters.put(key, Time.valueOf(value.toString()));
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
        logger.info("Fetching songs with filters: {}", castedFilters);
        return songDAO.findWithFilters(castedFilters);
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
