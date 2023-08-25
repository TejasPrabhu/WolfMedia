package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.AlbumDAO;
import com.tejasprabhu.wolfmedia.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AlbumService {

    private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);

    @Autowired
    private AlbumDAO albumDAO;

    public List<Album> findWithFilters(Map<String, Object> filters) {
        logger.info("Fetching albums with filters: {}", filters);
        return albumDAO.findWithFilters(filters);
    }

    @Transactional
    public Album save(Album album) {
        logger.info("Saving album: {}", album);
        albumDAO.save(album);
        return album;
    }

    @Transactional
    public Album update(Album album) {
        logger.info("Updating album with ID {}: {}", album.getAlbumID(), album);
        albumDAO.update(album);
        return album;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting album with ID: {}", id);
        albumDAO.delete(id);
    }
}
