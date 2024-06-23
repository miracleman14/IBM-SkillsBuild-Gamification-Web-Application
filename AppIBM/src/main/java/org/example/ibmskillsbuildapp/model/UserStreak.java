package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Represents the user streak entity in the application.
 */
@Entity
@Table(name = "UserStreak")
public class UserStreak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private int streakCount;
    private LocalDate lastLoginDate;

    // Constructors, getters, and setters

    /**
     * Default constructor for the UserStreak class.
     */
    public UserStreak() {
    }

    /**
     * Parameterized constructor for the UserStreak class.
     *
     * @param streakCount   The streak count for the user.
     * @param lastLoginDate The date of the user's last login.
     */
    public UserStreak(int streakCount, LocalDate lastLoginDate) {
        this.streakCount = streakCount;
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * Gets the user's ID.
     *
     * @return The user's ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user's ID.
     *
     * @param userId The user's ID.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the streak count for the user.
     *
     * @return The streak count.
     */
    public int getStreakCount() {
        return streakCount;
    }

    /**
     * Sets the streak count for the user.
     *
     * @param streakCount The streak count.
     */
    public void setStreakCount(int streakCount) {
        this.streakCount = streakCount;
    }

    /**
     * Gets the date of the user's last login.
     *
     * @return The date of the user's last login.
     */
    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Sets the date of the user's last login.
     *
     * @param lastLoginDate The date of the user's last login.
     */
    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
