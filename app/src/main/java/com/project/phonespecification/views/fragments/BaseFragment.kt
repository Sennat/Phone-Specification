package com.project.phonespecification.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.project.phonespecification.R
import com.project.phonespecification.dependency.DI

open class BaseFragment : Fragment() {

    // Initialize ViewModel
    protected val mainViewModelFragment by lazy {
        DI.provideViewModel(requireActivity())
    }

    // Initialize Firebase Auth
    protected val authInstance by lazy { FirebaseAuth.getInstance() }

}