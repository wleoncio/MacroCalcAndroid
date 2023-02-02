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

        // Start calculations when button is pressed
        sentSubmission.setOnClickListener {
            calcMacros(fatW, R.id.kcalFat)
        }
    }

    /* Calculate macros */
    private fun calcMacros(fatW: EditText, targetView: Int) {
        // Create a food item
        val food = Food()

        // Return energy information
        val kcal: TextView = findViewById(targetView)
        kcal.text = "${food.calcCal(fatW).roundToInt()} kcal from fat"
    }
}

/* Food item with macronutrient information */
class Food() {
    fun calcCal(grams: EditText): Double {
        val numGrams = grams.text.toString().toDouble()
        return 9 * numGrams
    }
}