package com.project.phonespecification.views.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.project.phonespecification.dependency.DI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    // initialising Firebase auth object
    protected val auth by lazy { FirebaseAuth.getInstance() }
    protected val authListener by lazy { FirebaseAuth.AuthStateListener {  } }

    // Initialize ViewModel
    protected val mainViewModelFragment by lazy {
        DI.provideViewModel(requireActivity())
    }

}