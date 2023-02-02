package com.rkgames.basisga

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rkgames.basisga.databinding.FragmentShopBinding

class ShopFragment : Fragment() {


    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private var mToast: Toast? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val sharedPreference = context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        if (!sharedPreference?.contains("attempts")!!) {
            editor?.putInt("attempts", 0)
            editor?.apply()
        }
        if (!sharedPreference.contains("isEasy")) {
            editor?.putBoolean("isEasy", false)
            editor?.apply()
        }
        val attemptsFinal = sharedPreference.getInt("attempts", 0)
        binding.tvAttempts.text = "You have $attemptsFinal attempts "
        val points=sharedPreference?.getInt("points",0)
        binding.tvPoints.text = points.toString()
        binding.switchEasyMode.isChecked = sharedPreference.getBoolean("isEasy", false) == true
        setListeners(sharedPreference,editor)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setListeners(
        sharedPreference: SharedPreferences?,
        editor: SharedPreferences.Editor?
    ) {
        binding.btnBuy.setOnClickListener {
            val attempts = sharedPreference?.getInt("attempts", 0)
            val attemptsToPut= attempts?.plus(1)
            editor?.putInt("attempts", attemptsToPut!!)
            editor?.apply()
            binding.tvAttempts.text = "You have $attemptsToPut attempts "
        }
        binding.switchEasyMode.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                editor?.putBoolean("isEasy", true)
                editor?.apply()
                mToast?.cancel()
                mToast = Toast.makeText(context, "Easy mode ON", Toast.LENGTH_SHORT)
                mToast?.show()
            } else {
                editor?.putBoolean("isEasy", false)
                editor?.apply()
                mToast?.cancel()
                mToast = Toast.makeText(context, "Easy mode OFF", Toast.LENGTH_SHORT)
                mToast?.show()
            }

        }
        binding.ibArrowBack.setOnClickListener {
            findNavController().navigate(
                ShopFragmentDirections.actionShopFragmentToMenuFragment())
        }
    }
}