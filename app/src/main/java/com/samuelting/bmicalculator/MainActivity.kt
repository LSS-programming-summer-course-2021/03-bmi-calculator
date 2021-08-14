package com.samuelting.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import java.lang.Exception
import kotlin.math.pow
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var calculateButton: Button
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weightEditText = findViewById<EditText>(R.id.weight)
        heightEditText = findViewById(R.id.height)
        calculateButton = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            try {
                val bmi = calculateBMI()
                var result = roundOff(bmi, 2)
                displayBMI(result)
            }catch (e: Exception){
                alertFormatError()
            }
        }
    }

    private fun calculateBMI(): Double {
        val height = heightEditText.text.toString().toDouble()
        val weight = weightEditText.text.toString().toDouble()
        return weight / height.pow(2)
    }

    private fun displayBMI(bmi: Double){
        var builder = AlertDialog.Builder(this)
            .setTitle("BMI Calualator")
            .setMessage("BMI: " + bmi.toString() + "  Status: " + getStatus(bmi))
            .setPositiveButton("OK", { _ , _ ->
                Log.d("test", "BMI calculated: " + bmi.toString() )
            })
            .show()
    }

    private fun getStatus(bmi: Double): String{
        if (bmi <= 18.5){
            return "過輕"
        }
        if (bmi > 18.5 && bmi <= 25){
            return "正常"
        }
        if (bmi > 25 && bmi <= 30){
            return "過重"
        }
        if (bmi > 30){
            return "癡肥"
        }
        return ""
    }

    private fun alertFormatError(){
        // TODO: Show alert box in case of errors
        AlertDialog.Builder(this)
            .setTitle("BMI Calculator")
            .setMessage("檢查你的數值格式")
            .show()
    }

    private fun roundOff(num: Double, decimalPlaces: Int): Double{
        val base = 10.0
        return round(num * base.pow(decimalPlaces)) /
                base.pow(decimalPlaces)
    }
}