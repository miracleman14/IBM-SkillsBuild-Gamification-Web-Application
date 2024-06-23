package org.example.ibmskillsbuildapp.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavBarController {

    @Autowired
    private AvatarService avatarService;

    @RequestMapping("/nav")
    public String navPage(HttpSession session) {
        // Retrieve the latest avatar data or set default values if no avatars are found
        List<Avatar> avatars = avatarService.getAllAvatars();
        Avatar latestAvatar = avatars.isEmpty() ? null : avatars.get(avatars.size() - 1);

        // Store the latest avatar in session for access in nav.jsp
        session.setAttribute("latestAvatar", latestAvatar != null ? latestAvatar : new Avatar());

        return "nav";
    }
}
