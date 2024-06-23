package org.example.ibmskillsbuildapp.controller;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AvatarService avatarService;

    @GetMapping("/viewLeaderboard")
    public String showLeaderboard(Model model, HttpSession session) {
        // Retrieve the currently logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User currentUser = repo.findByUserName(username);

        // Pass the current user to the model
        model.addAttribute("currentUser", currentUser);

        // Retrieve the avatar associated with the current user
        Avatar currentAvatar = currentUser.getAvatar();

        // Pass the avatar URL to the model
        model.addAttribute("currentUserAvatarURL",
            currentAvatar != null ? currentAvatar.getAvatarDataURL()
                : "/img/Null_Profile_Image.png");

        // Separate the current user from other players in the global leaderboard
        List<User> allPlayers = repo.findAllByOrderByScoreDesc();

        // Set default avatar URL for players who don't have an avatar
        for (User player : allPlayers) {
            Avatar avatar = player.getAvatar();
            if (avatar == null) {
                avatar = new Avatar(); // Create a new Avatar object
                avatar.setAvatarDataURL("/img/Null_Profile_Image.png");
                player.setAvatar(avatar);
            }
        }

        // Sort the allPlayers list based on their scores
        Collections.sort(allPlayers, Comparator.comparingInt(User::getScore).reversed());

        // Pass the allPlayers list to the model
        model.addAttribute("allPlayers", allPlayers);

        // Sort the friends list based on their scores
        List<User> friends = new ArrayList<>(currentUser.getFriends());
        if (!friends.contains(currentUser)) {
            friends.add(currentUser); // Add current user to friends list if not already present
        }
        Collections.sort(friends, Comparator.comparingInt(User::getScore).reversed());
        model.addAttribute("friends", friends);

        return "leaderboard";
    }
}
