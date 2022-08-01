package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.project.phonespecification.R
import com.project.phonespecification.databinding.FragmentRegisterBinding
import com.project.phonespecification.utils.InputValidator

class RegisterFragment : BaseFragment() {

    // Initialize viewBinding
    private lateinit var binding: FragmentRegisterBinding
    private val inputValidator by lazy { InputValidator() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        // Return back to login fragment
        binding.btnRegister.setOnClickListener {
            if (isPasswordsMatch(binding.inputPass1.text.toString(), binding.inputPass2.text.toString())) {
                binding.txtError.visibility = View.GONE
                registerUser(binding.inputUsername.text.toString(), binding.inputPass1.text.toString())
            }
        }

        // Return back to login fragment
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        return binding.root
    }

    private fun registerUser(username: String, pass: String) {
        if (isUserEmailValidated(username) && isUserPasswordValidated(pass)) {
            auth.createUserWithEmailAndPassword(username, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    } else {
                        binding.txtError.text.apply { "Registration is failed!}" }
                        binding.txtError.visibility = View.VISIBLE
                    }
                }

        }
    }

    private fun isPasswordsMatch(password1: String, password2: String): Boolean {
        return if (password1 == password2) {
            true
        } else {
            binding.txtError.text = "Password and Confirm Password don't match"
            binding.txtError.visibility = View.VISIBLE
            false
        }
    }

    private fun isUserEmailValidated(username: String): Boolean {
        return if (username.isNotBlank()) {
            if (inputValidator.isEmailValidated(username)) {
                true
            } else {
                binding.txtError.text = "Invalid email entered"
                binding.txtError.visibility = View.VISIBLE
                binding.inputUsername.setBackgroundResource(R.color.google)
                false
            }
        } else {
            binding.txtError.text = "Empty fields are not allowed"
            binding.txtError.visibility = View.VISIBLE
            binding.inputUsername.setBackgroundResource(R.color.google)
            false
        }
    }

    private fun isUserPasswordValidated(password: String): Boolean {
        return if (password.isNotBlank()) {
            if (inputValidator.isPasswordValidated(password)) {
                true
            } else {
                binding.txtError.text = "Invalid password entered"
                binding.txtError.visibility = View.VISIBLE
                false
            }
        } else {
            binding.txtError.text = "Empty fields are not allowed"
            binding.txtError.visibility = View.VISIBLE
            false
        }
    }
}