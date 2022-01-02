package com.alicimsamil.flightapp.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.Navigation
import com.alicimsamil.flightapp.R
import com.alicimsamil.flightapp.databinding.FragmentLoginScreenBinding
import com.alicimsamil.flightapp.presentation.MainActivity
import com.alicimsamil.flightapp.util.SharedPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreenFragment : Fragment() {

    private lateinit var binding: FragmentLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginScreenBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        checkFields()

    }


    private fun initListener() {

        binding.etEmailField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                checkFields()
                setBackgroundEdittext(p0.toString(), binding.etEmailField)
            }
        })


        binding.etPasswordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                checkFields()
                setBackgroundEdittext(p0.toString(), binding.etPasswordField)
            }
        })


        binding.btnLogin.setOnClickListener {
            val userEmail=SharedPref(requireContext()).getUserEmail()
            val userPassword=SharedPref(requireContext()).getUserPassword()
            val action = LoginScreenFragmentDirections.actionLoginScreenFragmentToMainScreenFragment()
            if (binding.etEmailField.text.toString()==userEmail && binding.etPasswordField.text.toString()==userPassword){
                if(binding.cbRememberState.isChecked){
                    SharedPref(requireContext()).setRememberState(true)
                    Navigation.findNavController(it).navigate(action)
                }else{
                    SharedPref(requireContext()).setRememberState(false)
                    Navigation.findNavController(it).navigate(action)
                }

            } else{
                Toast.makeText(context,getString(R.string.check_error),Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkFields() {

        if ((binding.etEmailField.text.toString().isNotEmpty()) && (binding.etEmailField.text.toString().isNotEmpty())
        ) {
            binding.btnLogin.alpha = 1F
            binding.btnLogin.isClickable = true
        } else {
            binding.btnLogin.alpha = 0.65F
            binding.btnLogin.isClickable = false
        }

    }


    private fun setBackgroundEdittext(text: String, editable: AppCompatEditText) {

        if (text.isNotEmpty()) {
            editable.setBackgroundDrawable(context?.getDrawable(R.drawable.et_active_background_shape))
        } else {
            editable.setBackgroundDrawable(context?.getDrawable(R.drawable.et_passive_background_shape))
        }


    }


}