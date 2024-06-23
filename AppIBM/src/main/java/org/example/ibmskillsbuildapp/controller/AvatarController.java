package org.example.ibmskillsbuildapp.controller;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.AvatarService;
import org.example.ibmskillsbuildapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AvatarController {

    private final AvatarService avatarService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AvatarController(AvatarService avatarService, UserService userService,
        UserRepository userRepository) {
        this.avatarService = avatarService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/avatar")
    public String avatarPage(Model model, Principal principal) {
        // Retrieve the authenticated user
        String username = principal.getName();
        User user = userRepository.findByUserName(username);

        // Retrieve the associated avatar
        Avatar avatar = user.getAvatar();

        if (avatar != null) {
            // Pass the avatar data to the avatar customization page
            model.addAttribute("skinColor", avatar.getSkinColor());
            model.addAttribute("eyeColor", avatar.getEyeColor());
            model.addAttribute("hairType", avatar.getHairType());
            model.addAttribute("hairColor", avatar.getHairColor());
            model.addAttribute("noseSize", avatar.getNoseSize());
            model.addAttribute("mouthSize", avatar.getMouthSize());
            model.addAttribute("glasses", avatar.isGlasses());
        } else {
            // No avatar found, set default data or handle as needed
            model.addAttribute("skinColor", "#ffddb3"); // Default to light skin color
            model.addAttribute("eyeColor", "#66533d"); // Default to brown eye color
            model.addAttribute("hairType", "curly"); // Default to curly hair type
            model.addAttribute("hairColor", "black"); // Default to black hair color
            model.addAttribute("noseSize", "medium"); // Default to medium nose size
            model.addAttribute("mouthSize", "medium"); // Default to medium mouth size
            model.addAttribute("glasses", false); // Default to no glasses
        }

        return "avatar";
    }

    @PostMapping("/saveAvatar")
    public ResponseEntity<String> saveAvatar(@RequestParam("avatar") MultipartFile avatarFile,
        @RequestParam("skinColor") String skinColor,
        @RequestParam("eyeColor") String eyeColor,
        @RequestParam("hairType") String hairType,
        @RequestParam("hairColor") String hairColor,
        @RequestParam("noseSize") String noseSize,
        @RequestParam("mouthSize") String mouthSize,
        @RequestParam(value = "glasses", required = false, defaultValue = "false") boolean glasses,
        HttpSession session) {
        try {
            // Process the avatarFile (MultipartFile)
            byte[] avatarData = avatarFile.getBytes();
            String avatarDataURL =
                "data:" + avatarFile.getContentType() + ";base64," + Base64.getEncoder()
                    .encodeToString(avatarData);

            // Get the authenticated user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            // Retrieve the user
            User user = userRepository.findByUserName(username);

            // Create or update the avatar
            Avatar avatar = user.getAvatar(); // Get the user's existing avatar if any, or create a new one
            if (avatar == null) {
                avatar = new Avatar();
            }

            // Set avatar properties
            avatar.setAvatarDataURL(avatarDataURL);
            avatar.setSkinColor(skinColor);
            avatar.setEyeColor(eyeColor);
            avatar.setHairType(hairType);
            avatar.setHairColor(hairColor);
            avatar.setNoseSize(noseSize);
            avatar.setMouthSize(mouthSize);
            avatar.setGlasses(glasses);

            // Set the avatar's user
            avatar.setUser(user);

            // Save the avatar
            avatarService.saveAvatar(avatar);

            return ResponseEntity.ok("Avatar saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to save avatar. Please try again later.");
        }
    }

    @RequestMapping("/profile")
    public String profilePage(Model model, HttpSession session, Principal principal) {
        // Retrieve the authenticated user
        String username = principal.getName();
        User user = userRepository.findByUserName(username);

        // Retrieve the associated avatar
        Avatar avatar = user.getAvatar();

        if (avatar != null) {
            // Pass the avatar data to the profile page
            session.setAttribute("avatarDataURL", avatar.getAvatarDataURL());
        } else {
            // No avatar found, set default data or handle as needed
            session.setAttribute("avatarDataURL", "/img/Null_Profile_Image.png");
        }

        model.addAttribute("username", username); // Pass the username to the profile page

        // Also add the avatarDataURL to the model for use in the nav bar
        model.addAttribute("avatarDataURL", session.getAttribute("avatarDataURL"));

        return "profile"; // Assuming your profile page JSP file is named profile.jsp
    }
}
