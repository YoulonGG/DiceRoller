package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRoll.setOnClickListener {
                rollDice()
            }
        }
    }
    private fun getRandomDiceImageResource(): Int {
        return when ((1..6).random()){
            1 -> R.drawable.die1
            2 -> R.drawable.die2
            3 -> R.drawable.die3
            4 -> R.drawable.die4
            5 -> R.drawable.die5
            else -> { R.drawable.die6 }
        }
    }
    private fun rollDice() {
        binding.apply {
            btnRoll.isEnabled = false
            val diceRoller = object : Runnable{
                var counter = 0
                override fun run() {
                    counter++
                    if (counter > 10) {
                        // Stop rolling after 1s
                        diceImage.setImageResource(getRandomDiceImageResource())
                        btnRoll.isEnabled = true
                    }else {
                        //Continue rolling
                        diceImage.setImageResource(getRandomDiceImageResource())
                        Handler(Looper.getMainLooper()).postDelayed(this, 100)
                    }
                }
            }
            Handler(Looper.getMainLooper()).postDelayed(diceRoller,0)
        }
    }

}