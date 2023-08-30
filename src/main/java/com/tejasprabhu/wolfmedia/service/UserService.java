package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.UserDAO;
import com.tejasprabhu.wolfmedia.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public List<User> findWithFilters(Map<String, Object> filters) {
        Map<String, Object> castedFilters = new HashMap<>();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ("songID".equals(key)) {
                castedFilters.put(key, Integer.valueOf(value.toString()));
            }
        }
        logger.info("Fetching users with filters: {}", castedFilters);
        return userDAO.findWithFilters(castedFilters);
    }

    @Transactional
    public User save(User user) {
        logger.info("Saving user: {}", user);
        userDAO.save(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        logger.info("Updating user with ID {}: {}", user.getUserID(), user);
        userDAO.update(user);
        return user;
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting user with ID: {}", id);
        userDAO.delete(id);
    }
}
