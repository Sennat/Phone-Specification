package com.project.phonespecification.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

class InputValidator {

    /**
     * Validate inputs against regex pattern
     * @param input
     * @param regex
     * @return
     */
    private fun isValidInput(input: String, regex: String): Boolean {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(input)
        return matcher.matches()
    }

    /**
     * Validate email address
     */
    fun isEmailValidated(email: String): Boolean {
        // Regex to check valid email.
        val emailRegex ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{3,6}$"
        return isValidInput(email, emailRegex)
    }

    /**
     * Validate email password
     */
    fun isPasswordValidated(password: String): Boolean {
        // Regex to check valid password.
        //val passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,20}$"
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$"
        return isValidInput(password, passwordRegex)
    }
}