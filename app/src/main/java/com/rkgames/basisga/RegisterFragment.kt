package com.rkgames.basisga

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rkgames.basisga.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val sharedPreference = context?.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        if(sharedPreference!!.contains("username")){
        val name= sharedPreference.getString("username","defaultname")
            Log.d("Tag", name.toString())
            binding.etReg.setText(name, TextView.BufferType.EDITABLE)
        }
        setListeners(sharedPreference)
        return binding.root
    }

    private fun setListeners(sharedPreference: SharedPreferences?) {
        binding.imageButton.setOnClickListener {
            if (binding.etReg.text?.isNotEmpty()!! && !binding.etReg.text?.contains(' ')!!) {
                var editor = sharedPreference?.edit()
                editor?.putString("username", binding.etReg.text.toString())
                editor?.apply()
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToMenuFragment()
                )
            } else {
                Toast.makeText(
                    context,
                    "Enter your user name without spaces,please",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}