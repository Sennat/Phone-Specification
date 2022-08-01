package com.project.phonespecification.inputTest

import com.project.phonespecification.utils.InputValidator
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class ValidateInputTest {

    lateinit var inputValidator: InputValidator

    @Before
    fun setup() {
        inputValidator = InputValidator()
    }

    @Test
    fun emailInputExpectedTrue() {
        val email = "test@yahoo.com"
        assertEquals(true, inputValidator.isEmailValidated(email) )
    }

    @Test
    fun passwordInputExpectedTrue() {
        // Password must have a number at the beginning and at least one character
        val password = "1Password@"
        assertEquals(true, inputValidator.isPasswordValidated(password))
    }

    @Test
    fun passwordInputExpectedFalse() {
        val password = "Password@34"
        assertEquals(true, inputValidator.isPasswordValidated(password)
        )
    }

}