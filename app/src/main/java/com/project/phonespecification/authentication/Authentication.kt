package com.project.phonespecification.authentication

import com.google.firebase.auth.FirebaseAuth
import com.project.phonespecification.utils.InputValidator
import dagger.Provides
import javax.inject.Singleton

class Authentication constructor(
    private val username: String,
    private val password1: String,
    private val password2: String
) {

    // Initialising Firebase auth object
    private val authentication by lazy { FirebaseAuth.getInstance() }

    // Initialising Firebase AuthStateListener
    private val authListener by lazy { FirebaseAuth.AuthStateListener { } }
    private val inputValidator by lazy { InputValidator() }

    fun isUserAuthenticated(username: String, password: String) {
        if (isUserEmailValidated(username) && isUserPasswordValidated(password)) {
            authentication.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) return@addOnCompleteListener
                }
        }
    }

    /*fun isUserRegistered(username: String, password1: String, password2: String) {
        if (isUserPasswordValidated(password1)) {
            if (password1 == password2) {
                authentication.createUserWithEmailAndPassword(username, password1)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            return@addOnCompleteListener
                        }
                    }
            }
            return true
        }

    }*/

    private fun isUserEmailValidated(username: String): Boolean {
        return if (username.isNotBlank()) {
            inputValidator.isEmailValidated(username)
        } else {
            false
        }
    }

    private fun isUserPasswordValidated(password: String): Boolean {
        return if (password.isNotBlank()) {
            inputValidator.isPasswordValidated(password)
        } else {
            false
        }
    }
}