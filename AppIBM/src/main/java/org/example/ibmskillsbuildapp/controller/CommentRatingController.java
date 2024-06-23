package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.Comment;
import org.example.ibmskillsbuildapp.model.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment-rating")
public class CommentRatingController {

    //this is showing the whole comment and rating form
    @GetMapping
    public String showCommentRating(Model model) {
        model.addAttribute("newComment", new Comment());
        model.addAttribute("newRating", new Rating());
        return "comment-rating";
    }

    //this is for adding comment from the user
    @PostMapping("/comment")
    public String addComment(@ModelAttribute Comment newComment) {
        return "redirect:/comment-rating";
    }

    @PostMapping("/rating")
    public String addRating(@ModelAttribute Rating newRating, BindingResult result, Model model) {
        if (!newRating.isValid()) {
            model.addAttribute("ratingError", "Rating must be between 1 and 5.");
            return "comment-rating";
        }
// this is for invalid rating value
        if (result.hasErrors()) {
            return "comment-rating";
        }
        return "redirect:/comment-rating";
    }
}
