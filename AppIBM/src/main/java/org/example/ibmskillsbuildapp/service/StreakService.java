package org.example.ibmskillsbuildapp.service;

import java.time.LocalDate;
import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.repo.UserStreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreakService {

    private final UserStreakRepository streakRepository;

    @Autowired
    public StreakService(UserStreakRepository streakRepository) {
        this.streakRepository = streakRepository;
    }


    /**
     * Updates the streak count for a user. If the user's last login date is not today, the streak
     * count is reset to 0. Otherwise, the streak count is incremented by 1 if the user logged in
     * continuously. The last login date is updated to today.
     *
     * @param userId the ID of the user whose streak is being updated.
     */
    public void updateStreak(Long userId) {
        // Retrieve the user's streak from the repository, or create a new streak if not found
        UserStreak userStreak = streakRepository.findById(userId).orElse(new UserStreak());
        LocalDate today = LocalDate.now();

        // If the last login date is not today, reset the streak count to 0
        if (userStreak.getLastLoginDate() == null || !userStreak.getLastLoginDate().plusDays(1)
            .equals(today)) {
            userStreak.setStreakCount(0);
        } else {
            // Check if the user logged in continuously
            if (userStreak.getLastLoginDate().plusDays(1).equals(today)) {
                userStreak.setStreakCount(userStreak.getStreakCount() + 1);
            }
        }

        // Update the last login date
        userStreak.setLastLoginDate(today);

        // Save the updated streak in the repository
        streakRepository.save(userStreak);
    }


    /**
     * Retrieves the user's streak based on the user ID. If the streak is not found in the
     * repository, returns a new UserStreak object.
     *
     * @param userId the ID of the user whose streak is being retrieved.
     * @return the user's streak, or a new UserStreak object if not found.
     */
    public UserStreak getUserStreak(Long userId) {
        return streakRepository.findById(userId).orElse(new UserStreak());
    }
}
