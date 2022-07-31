package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.navigation.fragment.findNavController
import com.project.phonespecification.R
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

        val user = binding.inputUsername.toString()
        val pass = binding.inputPassword.toString()


        binding.btnLogin.setOnClickListener {
            // Validate user authentication
            if (isUserInputValidated(user, pass)) {
                authenticateUser(user, pass)
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        return binding.root
    }

    /**
     * Authenticate a user
     */
    private fun authenticateUser(username: String, password: String) {
        authInstance.signInWithEmailAndPassword(username,password)
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPhoneCardFragment())
                } else {
                    binding.txtError.text.apply { "Authentication failed!" }
                    binding.txtError.visibility = View.VISIBLE
                }
            }
    }

    /**
     * Validate user authentication
     */
    private fun isUserInputValidated(user: String, pass: String): Boolean {
       val inputValidator = InputValidator()

        return if (inputValidator.isEmailValidated(user) && inputValidator.isPasswordValidated(pass)) {
            binding.txtError.visibility = View.GONE
            true
        } else {
            binding.inputUsername.setBoxBackgroundColorResource(R.color.google)
            binding.inputPassword.setBoxBackgroundColorResource(R.color.google)
            binding.txtError.visibility = View.VISIBLE
            false
        }
    }

}