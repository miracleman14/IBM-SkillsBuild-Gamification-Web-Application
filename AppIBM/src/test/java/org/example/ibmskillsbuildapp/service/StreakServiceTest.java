package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.repo.UserStreakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StreakServiceTest {

    @InjectMocks
    private StreakService streakService;

    @Mock
    private UserStreakRepository streakRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateStreak_FirstLogin() {
        Long userId = 1L;
        UserStreak userStreak = new UserStreak(); // Create a new UserStreak
        when(streakRepository.findById(userId)).thenReturn(Optional.of(userStreak));

        streakService.updateStreak(userId);

        assertEquals(0, userStreak.getStreakCount()); // Streak should start at 0
        assertEquals(LocalDate.now(),
            userStreak.getLastLoginDate()); // Last login date should be today
    }

    @Test
    public void testUpdateStreak_ContinuousLogin() {
        Long userId = 1L;
        UserStreak userStreak = new UserStreak();
        userStreak.setLastLoginDate(LocalDate.now().minusDays(1)); // User logged in yesterday
        when(streakRepository.findById(userId)).thenReturn(Optional.of(userStreak));

        streakService.updateStreak(userId);

        assertEquals(1, userStreak.getStreakCount()); // Streak count should increase by 1
        assertEquals(LocalDate.now(),
            userStreak.getLastLoginDate()); // Last login date should be today
    }

    @Test
    public void testUpdateStreak_StreakReset() {
        Long userId = 1L;
        UserStreak userStreak = new UserStreak();
        userStreak.setLastLoginDate(LocalDate.now().minusDays(2)); // User didn't log in for 2 days
        userStreak.setStreakCount(5); // User had a streak of 5 days
        when(streakRepository.findById(userId)).thenReturn(Optional.of(userStreak));

        streakService.updateStreak(userId);

        assertEquals(0, userStreak.getStreakCount()); // Streak count should reset to 0
        assertEquals(LocalDate.now(),
            userStreak.getLastLoginDate()); // Last login date should be today
    }

    @Test
    public void testUpdateStreak_NoPreviousLogin() {
        Long userId = 1L;
        UserStreak userStreak = new UserStreak();
        userStreak.setStreakCount(3); // User had a streak before but no previous login date
        when(streakRepository.findById(userId)).thenReturn(Optional.of(userStreak));

        streakService.updateStreak(userId);

        assertEquals(0, userStreak.getStreakCount()); // Streak count should reset to 0
        assertEquals(LocalDate.now(),
            userStreak.getLastLoginDate()); // Last login date should be today
    }

    // Add more test cases as needed
}
