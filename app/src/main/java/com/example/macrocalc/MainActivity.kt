package com.example.macrocalc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign references to relevant UI objects
        val sentSubmission: Button = findViewById(R.id.submitButton)
        val fatW: EditText = findViewById(R.id.fatWeight)
        val carbW: EditText = findViewById(R.id.carbWeight)
        val proteinW: EditText = findViewById(R.id.proteinWeight)

        // Start calculations when button is pressed
        sentSubmission.setOnClickListener {
            val food = Food(fatW, carbW, proteinW)
            calcMacros(food)
        }
    }

    /* Calculate macros */
    private fun calcMacros(food: Food) {
        // Return energy information
        val kcalFat: TextView = findViewById(R.id.kcalFat)
        val kcalCarb: TextView = findViewById(R.id.kcalCarb)
        val kcalProtein: TextView = findViewById(R.id.kcalProtein)
        val kcalTotal: TextView = findViewById(R.id.kcalTotal)
        val pctCarbs: TextView = findViewById(R.id.pctCarbs)
        kcalFat.text = "(${food.calcCal(food.fat, 9).roundToInt()} fat"
        kcalCarb.text = "${food.calcCal(food.carb, 4).roundToInt()} carbs"
        kcalProtein.text = "${food.calcCal(food.protein, 4).roundToInt()} protein)"
        kcalTotal.text = "${food.calcTotalCal()} kcal total"
        pctCarbs.text = "${food.pctCarbs().roundToInt()} % from carbs"
    }
}

/* Food item with macronutrient information */
class Food (fatW: EditText, carbW: EditText, proteinW: EditText) {
    val fat = fatW.text.toString().toDouble()
    val carb = carbW.text.toString().toDouble()
    val protein = proteinW.text.toString().toDouble()

    fun calcCal(grams: Double, multiplier: Int): Double {
        return multiplier * grams
    }
    fun calcTotalCal(): Double {
        return fat * 9 + (carb + protein) * 4
    }
    fun pctCarbs(): Double {
        val totalEnergy = fat * 9 + (carb + protein) * 4
        return carb * 4 / totalEnergy * 100
    }
}