package com.rkgames.basisga

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rkgames.basisga.databinding.FragmentMenuBinding
import kotlin.system.exitProcess

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val sharedPreference = context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val name=sharedPreference?.getString("username","defaultName")
        binding.tvUserName.text="Good luck, $name"
        setListeners()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setListeners() {
        binding.btnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_gameFragment)
        }
        binding.btnExit.setOnClickListener {
            exitProcess(0)
        }
        binding.btnRules.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_rulesFragment)
        }
        binding.btnShop.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_shopFragment) }
    }


}