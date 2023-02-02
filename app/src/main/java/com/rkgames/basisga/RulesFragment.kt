package com.rkgames.basisga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rkgames.basisga.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {
    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
        setListeners()

        // Inflate the layout for this fragment
        return binding.root
    }
    private fun setListeners() {
        binding.ibArrowBack.setOnClickListener {
            findNavController().navigate(
                RulesFragmentDirections.actionRulesFragmentToMenuFragment())
        }
    }
}