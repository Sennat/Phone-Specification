package com.project.phonespecification.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.project.phonespecification.databinding.FragmentLoginBinding
import com.project.phonespecification.utils.Constants.USER_ID
import com.project.phonespecification.utils.InputValidator

class LoginFragment : BaseFragment() {

    // Initialize viewBinding
    private lateinit var binding: FragmentLoginBinding
    private val sharedPreferences by lazy { requireContext().getSharedPreferences(USER_ID, Context.MODE_PRIVATE)}
    private val USER_EMAIL = "user_email"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.inputUsernameText.setText(rememberUsername())

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
                if (binding.checkedBox.isChecked) {
                    var editor = sharedPreferences.edit()
                    editor.putString(USER_EMAIL, binding.inputUsernameText.text.toString())
                    editor.commit()
                }

                binding.checkedBox.isChecked = false
                binding.inputUsernameText.text?.clear()
                binding.inputPasswordText.text?.clear()
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
                    mainViewModelFragment.setBrandLoading()
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

    private fun rememberUsername(): String? {
        return if (sharedPreferences.getString(USER_EMAIL, "") != "") {
            binding.checkedBox.isChecked = true
            sharedPreferences.getString(USER_EMAIL, "")
        } else {
            binding.checkedBox.isChecked = false
            null
        }
    }

    /*override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            mainViewModelFragment.setBrandLoading()
            findNavController().navigate(PhoneCardFragmentDirections.actionPhoneCardFragmentToLoginFragment())
        } else {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPhoneCardFragment())
        }
    }*/

}