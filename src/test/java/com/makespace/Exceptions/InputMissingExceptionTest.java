package com.makespace.Exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputMissingExceptionTest {

    @Test
    @DisplayName("Exception info print test")
    void testToString() {
        assertEquals(new InputMissingException().toString(), "Input File destination not provided");
    }
}