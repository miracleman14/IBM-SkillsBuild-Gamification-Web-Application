package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RatingTest {

    @Test
    void testSetValueAndGetValue() {
        Rating rating = new Rating();
        rating.setValue(2);
        assertEquals(2, rating.getValue());
    }

    @Test
    void testIsValidWithValidRating() {
        Rating rating = new Rating();
        rating.setValue(3);
        assertTrue(rating.isValid());
    }

    @Test
    void testIsValidWithInvalidRating() {
        Rating rating = new Rating();
        rating.setValue(6);
        assertFalse(rating.isValid());
    }

    @Test
    void testDefaultConstructor() {
        Rating rating = new Rating();
        assertEquals(0, rating.getValue());
    }
}
