package com.example.envoy.kotlinplayground


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_dice.*
import java.util.*

/**
 * Created by Envoy on 12/22/17.
 */
class DiceActivity : AppCompatActivity() {
    private val ROLL_RESULT = "rolls_result"
    private val rand = Random()
    private lateinit var rolls_result: TextView


    private fun roll(sides:Int, multiplier:Int = 1, modifier: Int = 0){
        rolls_result = findViewById<TextView>(R.id.roll_result)
        var roll_total = 0
        var roll_text = "$multiplier d $sides"

        if(modifier > 0){
            roll_text += " + $modifier"
        }

        for(die in IntRange(1,multiplier)) {
            roll_total += (rand.nextInt(sides)) + 1
        }

        roll_total += modifier
        roll_text += ": " + roll_total.toString()

        rolls_result.text = roll_text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dice)

        val d100_button = findViewById<TextView>(R.id.d100_button)
        val d20_button = findViewById<TextView>(R.id.d20_button)
        val d12_button = findViewById<TextView>(R.id.d12_button)
        val d10_button = findViewById<TextView>(R.id.d10_button)
        val d8_button = findViewById<TextView>(R.id.d8_button)
        val d6_button = findViewById<TextView>(R.id.d6_button)
        val d4_button = findViewById<TextView>(R.id.d4_button)
        val attack_button = findViewById<TextView>(R.id.attack_button)
        var attack_modifier = findViewById<TextView>(R.id.attack_modifier)
        val damage_button = findViewById<TextView>(R.id.damage_button)
        var damage_multiplier = findViewById<TextView>(R.id.dice_multiplier)
        var damage_dice = findViewById<Spinner>(R.id.dice_spinner)
        var damage_modifier = findViewById<TextView>(R.id.damage_modifier)

        d100_button.setOnClickListener { roll(100) }
        d20_button.setOnClickListener { roll(20) }
        d12_button.setOnClickListener { roll(12) }
        d10_button.setOnClickListener { roll(10) }
        d8_button.setOnClickListener { roll(8) }
        d6_button.setOnClickListener { roll(6) }
        d4_button.setOnClickListener { roll(4) }
        attack_button.setOnClickListener{
            if(attack_modifier.text.toString() == ""){
                attack_modifier.text = "0"
            }
            roll(20, 1, Integer.parseInt(attack_modifier.text?.toString()) )
        }
        damage_button.setOnClickListener{
            if(damage_multiplier.text.toString() == ""){
                damage_multiplier.text = "1"
            }
            if(damage_modifier.text.toString() == ""){
                damage_modifier.text = "0"
            }
            roll(Integer.parseInt(damage_dice.selectedItem.toString()), Integer.parseInt(damage_multiplier.text?.toString()), Integer.parseInt(damage_modifier.text?.toString()) )
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(ROLL_RESULT,rolls_result.text.toString())

        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        rolls_result = findViewById<TextView>(R.id.roll_result)
        rolls_result.text = savedInstanceState?.getString(ROLL_RESULT)
    }
}