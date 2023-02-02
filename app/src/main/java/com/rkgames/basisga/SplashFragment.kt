package com.rkgames.basisga

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.rkgames.basisga.ApplicationIcyLuck.retu
import com.rkgames.basisga.databinding.FragmentSplashBinding
import com.rkgames.basisga.startIcyLuck.StartIcyLuckInterface

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        animateViews()
        if (retu) {
            startIcyLuck.start { start ->
                if (start) {
                    findNavController().navigate(
                        R.id.icyLuckWeActivity)
                } else {
                    try {
                        findNavController().navigate(
                            SplashFragmentDirections.actionSplashFragmentToRegisterFragment()
                        )
                    } catch (e: Exception) {
                        e.message
                    }

                }
            }
        } else {
            findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToRegisterFragment()
            )
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun animateViews() {
        binding.imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate))
        binding.textView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in_out))

    }

}