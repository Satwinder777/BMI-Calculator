package com.example.bmicalculator

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.numEt2)
        val heightText = findViewById<EditText>(R.id.numEt)
        val button = findViewById<Button>(R.id.btn)

        button.setOnClickListener() {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateInput(weight, height)) {


                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digit = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2Digit)
            }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{
      return when{
          weight.isNullOrEmpty() ->{
            Toast.makeText(this, "please, enter your Weight", Toast.LENGTH_LONG).show()
          return false
          }
          height.isNullOrEmpty() ->{
              Toast.makeText(this, "Height is Empty", Toast.LENGTH_LONG).show()
              return false
          }
          else ->{
              return true
          }

      }
    }
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.textView50)
        val descLine = findViewById<TextView>(R.id.textView7)
        val info = findViewById<TextView>(R.id.textView5)

        resultIndex.text = bmi.toString()
        info.text = "(the normal Range is 18.7 to 28.9)"

        var resultText = " "
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = " Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 24.99..29.99 ->{
                resultText = "OverWeight"
                color = R.color.overweight
            }
            bmi>30.00 ->{
                resultText = "to overWeight"
                color = R.color.obese
            }
        }
        descLine.setTextColor(ContextCompat.getColor(this,color))
        descLine.text =resultText

    }
}