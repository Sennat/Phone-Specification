package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.project.phonespecification.databinding.FragmentLoginBinding
import com.project.phonespecification.utils.InputValidator

class LoginFragment : BaseFragment() {

    // Initialize viewBinding
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)

        // Validate user authentication
        binding.btnLogin.setOnClickListener {
            loginToApp(
                binding.inputUsernameText.text.toString(),
                binding.inputPasswordText.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        return binding.root
    }

    /**
     *  Login to app
     */
    private fun loginToApp(user: String, pass: String) {
        binding.txtError.visibility = View.GONE
        if (isInputValidated(binding.inputUsername, binding.inputPassword)) {
            if (isUserEmailAndPasswordValidated(user, pass)) {
                authenticateUser(user, pass)
            }
        } else {
            binding.txtError.text.apply { "Authentication failed!" }
            binding.txtError.visibility = View.VISIBLE
        }
    }

    /**
     * Authenticate a user
     */
    private fun authenticateUser(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    mainViewModelFragment.setLoading()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPhoneCardFragment())
                } else {
                    binding.txtError.text = "Authentication failed!}"
                    binding.txtError.visibility = View.VISIBLE
                }
            }
    }

    /**
     * Validate user authentication
     */
    private fun isUserEmailAndPasswordValidated(user: String, pass: String): Boolean {
        return if (InputValidator().isEmailValidated(user) && InputValidator().isPasswordValidated(pass)
        ) { ///later need email patter validation
            binding.txtError.visibility = View.GONE
            true
        } else {
            binding.txtError.visibility = View.VISIBLE
            false
        }
    }

    private fun isInputValidated(
        inputUsername: TextInputLayout,
        inputPassword: TextInputLayout
    ): Boolean {
        return if (inputUsername.toString().isNotBlank() && inputPassword.toString().isNotBlank()) {
            true
        } else {
            binding.txtError.text.apply { "Input can't be empty" }
            false
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

}