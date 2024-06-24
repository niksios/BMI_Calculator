package com.niks.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.edtWeight)
        val heightText = findViewById<EditText>(R.id.edtHeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val wt = weightText.text.toString()
            val ht = heightText.text.toString()

            if (validateInput(wt, ht)) {
                val bmi = wt.toFloat() / ((ht.toFloat() / 100) * (ht.toFloat() / 100))
                //get result with 2 decimal places
                val bmi_val = String.format("%.2f", bmi).toFloat()

                displayResult(bmi_val)
            }
        }
    }

    private fun validateInput(wt:String?, ht:String?):Boolean{
        return when{
            wt.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight field empty!", Toast.LENGTH_LONG).show()
                return false
            }
            ht.isNullOrEmpty() -> {
                Toast.makeText(this, "Height field empty!", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }

        }
    }

    private fun displayResult(bmi:Float){

        val tvIndex = findViewById<TextView>(R.id.tvIndex)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvInfo = findViewById<TextView>(R.id.tvInfo)

        tvIndex.text = bmi.toString()
        tvInfo.text = "Normal range is 18.5 to 24.9"

        var tvres = ""
        var color = 0

        when{
            bmi<18.50 -> {
                tvres = "Underweight"
                color = android.R.color.holo_blue_dark
            }
            bmi in 18.5..24.99->{
                tvres = "Healthy"
                color = android.R.color.holo_green_dark
            }
            bmi in 25.00..29.99->{
                tvres = "Overweight"
                color = android.R.color.holo_orange_dark
            }
            bmi > 29.99->{
                tvres = "Obese"
                color = android.R.color.holo_red_dark
            }
        }

        tvResult.setTextColor(ContextCompat.getColor(this, color))
        tvResult.text = tvres


    }
}