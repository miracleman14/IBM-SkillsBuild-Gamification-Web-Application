package org.example.ibmskillsbuildapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.ibmskillsbuildapp.model.Comment;
import org.example.ibmskillsbuildapp.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

class CommentRatingControllerTest {

    private CommentRatingController controller;
    private Model model;
    private Comment comment;
    private Rating rating;

    @BeforeEach
    void setUp() {
        controller = new CommentRatingController();
        model = mock(Model.class);
        comment = new Comment();
        rating = new Rating();
    }

    @Test
    void testShowCommentRating() {
        String viewName = controller.showCommentRating(model);
        assertEquals("comment-rating", viewName);
        verify(model, times(1)).addAttribute(eq("newComment"), any(Comment.class));
        verify(model, times(1)).addAttribute(eq("newRating"), any(Rating.class));
    }

    @Test
    void testAddComment() {
        String redirect = controller.addComment(comment);
        assertEquals("redirect:/comment-rating", redirect);
    }

    @Test
    void testAddValidRating() {
        rating.setValue(2);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        String redirect = controller.addRating(rating, result, model);
        assertEquals("redirect:/comment-rating", redirect);
        verify(model, times(0)).addAttribute(eq("ratingError"), anyString());
    }

    @Test
    void testAddInvalidRating() {
        rating.setValue(6);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String viewName = controller.addRating(rating, result, model);
        assertEquals("comment-rating", viewName);
        verify(model, times(1)).addAttribute(eq("ratingError"), anyString());
    }
}
