package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.springframework.data.repository.CrudRepository;

public interface UserStreakRepository extends CrudRepository<UserStreak, Long> {

    /**
     * Finds a user's streak by their user ID.
     *
     * @param userId the ID of the user.
     * @return the user's streak, or null if not found.
     */
    UserStreak findByUserId(Long userId);
}