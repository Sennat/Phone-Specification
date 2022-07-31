package com.project.phonespecification.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
        val username = binding.inputUsername.toString()
        val password1 = binding.inputPassword1.toString()
        val password2 = binding.inputPassword1.toString()

        // Return back to login fragment
        binding.btnRegister.setOnClickListener {
            if (password1 == password2) {
                registerUser(username, password1)
            } else {
                binding.txtError.text = "Passwords aren't match!"
                binding.txtError.visibility = View.VISIBLE
            }
        }

        // Return back to login fragment
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        return binding.root
    }

    private fun registerUser(username: String, pass: String) {
        if (isUserInputValidated(username) && isPasswordInputValidated(pass)) {
            authInstance.createUserWithEmailAndPassword(username, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.inputUsername.visibility = View.GONE
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    }
                }

        }
    }

    private fun isUserInputValidated(username: String): Boolean {
        return if (inputValidator.isEmailValidated(username)) {
            binding.txtError.visibility = View.GONE
            true
        } else {
            binding.txtError.text = "You have entered invalid email."
            binding.txtError.visibility = View.VISIBLE
            binding.inputUsername.setBackgroundResource(R.color.google)
            false
        }
    }

    private fun isPasswordInputValidated(password: String): Boolean {
        return if (inputValidator.isPasswordValidated(password)) {
            binding.txtError.visibility = View.GONE
            true
        } else {
            binding.txtError.text = "You have entered invalid password."
            binding.txtError.visibility = View.VISIBLE
            binding.inputPassword1.setBackgroundResource(R.color.google)
            false
        }
    }
}