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
        val totalKcal: TextView = findViewById(R.id.kcalTotal)

        // Start calculations when button is pressed
        sentSubmission.setOnClickListener {
            calcMacros(fatW, R.id.kcalFat, 9)
            calcMacros(carbW, R.id.kcalCarb, 4)
            calcMacros(proteinW, R.id.kcalProtein, 4)
        }
    }

    /* Calculate macros */
    private fun calcMacros(fatW: EditText, targetView: Int, multiplier: Int) {
        // Create a food item
        val food = Food()

        // Return energy information
        val kcal: TextView = findViewById(targetView)
        kcal.text = "${food.calcCal(fatW, multiplier).roundToInt()} kcal"
    }
}

/* Food item with macronutrient information */
class Food() {
    fun calcCal(grams: EditText, multiplier: Int): Double {
        val numGrams = grams.text.toString().toDouble()
        return multiplier * numGrams
    }
}