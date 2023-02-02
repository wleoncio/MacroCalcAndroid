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
        val kcalFatView: TextView = findViewById(R.id.kcalFat)
        val kcalCarbView: TextView = findViewById(R.id.kcalCarb)
        val kcalProteinView: TextView = findViewById(R.id.kcalProtein)
        val kcalTotalView: TextView = findViewById(R.id.kcalTotal)
        val pctCarbsView: TextView = findViewById(R.id.pctCarbs)
        val kcalFat = food.calcCal(food.fat, 9)
        val kcalCarb = food.calcCal(food.carb, 4)
        val kcalProtein = food.calcCal(food.protein, 4)
        val kcalTotal = kcalFat + kcalCarb + kcalProtein
        val pctCarb = kcalCarb / kcalTotal * 100
        kcalFatView.text = "($kcalFat fat"
        kcalCarbView.text = "$kcalCarb carbs"
        kcalProteinView.text = "$kcalProtein protein)"
        kcalTotalView.text = "$kcalTotal kcal total"
        pctCarbsView.text = "${pctCarb.roundToInt()} % from carbs"
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
}