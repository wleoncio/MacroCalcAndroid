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
            calcMacros(fatW, carbW, proteinW)
        }
    }

    /* Calculate macros */
    private fun calcMacros(fatW: EditText, carbW: EditText, proteinW: EditText) {
        // Create a food item
        val food = Food()

        // Return energy information
        val kcalFat: TextView = findViewById(R.id.kcalFat)
        val kcalCarb: TextView = findViewById(R.id.kcalCarb)
        val kcalProtein: TextView = findViewById(R.id.kcalProtein)
        val kcalTotal: TextView = findViewById(R.id.kcalTotal)
        val pctCarbs: TextView = findViewById(R.id.pctCarbs)
        kcalFat.text = "${food.calcCal(fatW, 9).roundToInt()} kcal fat"
        kcalCarb.text = "${food.calcCal(carbW, 4).roundToInt()} kcal carbs"
        kcalProtein.text = "${food.calcCal(proteinW, 4).roundToInt()} kcal protein"
        kcalTotal.text = "${food.calcTotalCal(fatW, carbW, proteinW)} kcal total"
        pctCarbs.text = "${food.pctCarbs(fatW, carbW, proteinW).roundToInt()} % from carbs"
    }
}

/* Food item with macronutrient information */
class Food() {
    fun calcCal(grams: EditText, multiplier: Int): Double {
        val numGrams = grams.text.toString().toDouble()
        return multiplier * numGrams
    }
    fun calcTotalCal(fatG: EditText, carbG: EditText, proteinG: EditText): Double {
        val fat = fatG.text.toString().toDouble()
        val carb = carbG.text.toString().toDouble()
        val protein = proteinG.text.toString().toDouble()
        return fat * 9 + (carb + protein) * 4
    }
    fun pctCarbs(fatG: EditText, carbG: EditText, proteinG: EditText): Double {
        val fat = fatG.text.toString().toDouble()
        val carb = carbG.text.toString().toDouble()
        val protein = proteinG.text.toString().toDouble()
        val totalEnergy = fat * 9 + (carb + protein) * 4
        return carb * 4 / totalEnergy * 100
    }
}