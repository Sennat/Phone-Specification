package com.project.phonespecification.views.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.project.phonespecification.dependency.DI
import com.project.phonespecification.models.PhoneDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    // Initialising Firebase auth object
    protected val auth by lazy { FirebaseAuth.getInstance() }
    // Initialising Firebase AuthStateListener
    protected val authListener by lazy { FirebaseAuth.AuthStateListener {  } }
    // Initialize ViewModel
    protected val mainViewModelFragment by lazy {DI.provideViewModel(requireActivity())}


}