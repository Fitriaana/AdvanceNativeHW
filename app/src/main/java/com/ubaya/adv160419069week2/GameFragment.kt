package com.ubaya.adv160419069week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random
import kotlin.text.toInt as toInt


class GameFragment : Fragment() {
    //variable
    var score = 0
    var rightAnswer=0
    var num1=0
    var num2= 0
    var playerName: String? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
   //function tambahan
    fun calculate(){
        val randomInteger = (1..50).shuffled()
        num1 = randomInteger.first()
        txtNumberRand1.text = num1.toString()
        num2 =  randomInteger.last()
        txtNumberRand2.text =num2.toString()
        rightAnswer = num1 + num2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text="$playerName's Turn"
        }
        calculate()
        btnSubmit.setOnClickListener {
            if (rightAnswer.toString() == txtAnswer.text.toString()) {
                score += 1
                Toast.makeText(activity, "Yeay your answer is correct", Toast.LENGTH_SHORT).show()
                calculate()
                txtAnswer.text?.clear()
            }
            else {
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}