package com.alicimsamil.flightapp.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alicimsamil.flightapp.R
import com.alicimsamil.flightapp.util.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SharedPref(requireContext()).saveUser(getString(R.string.email),getString(R.string.password))
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            if (SharedPref(requireContext()).checkRememberState()){
                val action=SplashScreenFragmentDirections.actionSplashScreenFragmentToMainScreenFragment()
                Navigation.findNavController(view).navigate(action)
            } else {
                val action=SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginScreenFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }

    }


}