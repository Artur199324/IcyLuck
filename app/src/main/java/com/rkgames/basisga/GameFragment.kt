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
import com.rkgames.basisga.databinding.FragmentGameBinding
import kotlin.math.abs

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var mToast: Toast? = null
    private var chooseNumber=0

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.tvMyNumber.text=chooseNumber.toString()
        val sharedPreference = context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        val attemptsFinal = sharedPreference?.getInt("attempts", 0)
        val points=sharedPreference?.getInt("points",0)
        binding.tvAttemptShow.text = "You have $attemptsFinal attempts "
        binding.tvPoints.text = points.toString()
        if (!sharedPreference?.contains("points")!!) {
            editor?.putInt("points", 0)
            editor?.apply()
        }
        setListeners()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setListeners() {
        binding.ibArrowBack.setOnClickListener {
            findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToMenuFragment())
        }
        binding.ibAttemptAdd.setOnClickListener {
            if(chooseNumber!=20){
                chooseNumber++
                binding.tvMyNumber.text=chooseNumber.toString()
            }
        }
        binding.ibAttemptMinus.setOnClickListener {
            if(chooseNumber!=0){
                chooseNumber--
                binding.tvMyNumber.text=chooseNumber.toString()
            }
        }
        binding.btnPlay.setOnClickListener {
            val sharedPreference = context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference?.edit()
            if(sharedPreference?.getInt("attempts", 0)!! >=1){
                val attempts = sharedPreference.getInt("attempts", 0)
                val attemptsToPut= attempts.minus(1)
                editor?.putInt("attempts", attemptsToPut)
                editor?.apply()

                gameLogic(sharedPreference,editor)
            }
            else {
                mToast?.cancel()
                mToast = Toast.makeText(context, "Please,buy new attempts!", Toast.LENGTH_SHORT)
                mToast?.show()
            }
            val attemptsFinal = sharedPreference?.getInt("attempts", 0)
            binding.tvAttemptShow.text = "You have $attemptsFinal attempts "
        }

    }

    private fun gameLogic(sharedPreference: SharedPreferences, editor: SharedPreferences.Editor?) {
        if(!sharedPreference.getBoolean("isEasy", false)){
            val randomNumber=(1..20).random()
            binding.tvRandomNumber.text = randomNumber.toString()
            val oppNumber=(1..20).random()
            binding.tvOppNumber.text = oppNumber.toString()
            val diffMy=abs(chooseNumber-randomNumber)
            val diffOpp= abs(oppNumber-randomNumber)
            if(diffMy>diffOpp){
                mToast?.cancel()
                mToast = Toast.makeText(context, "You lose!", Toast.LENGTH_SHORT)
                mToast?.show()
            }
            else if(diffMy<diffOpp){
                mToast?.cancel()
                mToast = Toast.makeText(context, "You win!", Toast.LENGTH_SHORT)
                mToast?.show()
                val points = sharedPreference?.getInt("points", 0)
                val pointsToPut= points?.plus(1)
                editor?.putInt("points", pointsToPut!!)
                editor?.apply()
                binding.tvPoints.text=pointsToPut.toString()
            }
            else{
                mToast?.cancel()
                mToast = Toast.makeText(context, "Draw!", Toast.LENGTH_SHORT)
                mToast?.show()
            }
        }else{
            val randomNumber=(1..20).random()
            binding.tvRandomNumber.text = randomNumber.toString()
            val oppNumber=(2..19).random()
            binding.tvOppNumber.text = oppNumber.toString()
            val diffMy=abs(chooseNumber-randomNumber)
            val diffOpp= abs(oppNumber-randomNumber)
            if(diffMy>diffOpp){
                mToast?.cancel()
                mToast = Toast.makeText(context, "You lose!", Toast.LENGTH_SHORT)
                mToast?.show()
            }
            else if(diffMy<diffOpp){
                mToast?.cancel()
                mToast = Toast.makeText(context, "You win!", Toast.LENGTH_SHORT)
                mToast?.show()
                val points = sharedPreference?.getInt("points", 0)
                val pointsToPut= points?.plus(1)
                editor?.putInt("points", pointsToPut!!)
                editor?.apply()
                binding.tvPoints.text=pointsToPut.toString()
            }
            else{
                mToast?.cancel()
                mToast = Toast.makeText(context, "Draw!", Toast.LENGTH_SHORT)
                mToast?.show()
            }
        }
    }
}