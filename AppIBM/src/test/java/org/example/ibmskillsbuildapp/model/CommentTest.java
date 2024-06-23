package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CommentTest {

    @Test
    void testSetTextAndGetText() {
        String commentText = "This is a test comment.";
        Comment comment = new Comment();
        comment.setText(commentText);
        assertEquals(commentText, comment.getText());
    }

    @Test
    void testDefaultConstructor() {
        Comment comment = new Comment();
        assertNull(comment.getText());
    }
}
