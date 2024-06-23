package org.example.ibmskillsbuildapp.service;

import java.util.List;
import java.util.Optional;
import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.repo.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Avatar saveAvatar(Avatar avatar) {
        try {
            // Log avatar details for debugging
            System.out.println("Saving Avatar: " + avatar.toString());

            // Save avatar to the database
            return avatarRepository.save(avatar);
        } catch (DataAccessException e) {
            // Log the exception with a logger framework
            // e.g., logger.error("Failed to save avatar.", e);
            throw new RuntimeException("Failed to save avatar: " + e.getMessage(), e);
        }
    }

    public Optional<Avatar> getAvatarById(Long id) {
        return avatarRepository.findById(id);
    }

    public List<Avatar> getAllAvatars() {
        return (List<Avatar>) avatarRepository.findAll();
    }

    public Avatar getMostRecentAvatar() {
        // Retrieve the most recent avatar from the database
        List<Avatar> avatars = (List<Avatar>) avatarRepository.findAll();
        if (!avatars.isEmpty()) {
            // Sort the avatars by ID in descending order to get the most recent one
            avatars.sort((a1, a2) -> a2.getId().compareTo(a1.getId()));
            return avatars.get(0);
        } else {
            return null; // Return null if no avatars are found
        }
    }
    // Add more methods as needed
}
