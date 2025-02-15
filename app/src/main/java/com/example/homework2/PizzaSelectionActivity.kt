package com.example.homework2

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PizzaSelectionActivity : AppCompatActivity() {
    private var subTotal: Double = 0.00
    private var size = ""

    private val smallPrice = 10.29
    private val mediumPrice = 12.59
    private val largePrice = 14.89

    private var numToppings = 0
    private val smallTopping = 1.39
    private val medTopping = 2.21
    private val largeTopping = 2.29

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pizza_selection)

        val orderActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                val data = result.data
            }
        }

        findViewById<Button>(R.id.button_Checkout).setOnClickListener{
            val myIntent = Intent(this, PizzaOrderActivity::class.java)

            // if a type has been chosen
            if(findViewById<RadioButton>(R.id.radioButton_Pepperoni).isChecked ||
                findViewById<RadioButton>(R.id.radioButton_Bbq).isChecked ||
                findViewById<RadioButton>(R.id.radioButton_Margherita).isChecked ||
                findViewById<RadioButton>(R.id.radioButton_Hawaiian).isChecked){
                // if a size has been chosen
                if(findViewById<RadioButton>(R.id.radioButton_Small).isChecked ||
                    findViewById<RadioButton>(R.id.radioButton_Medium).isChecked ||
                    findViewById<RadioButton>(R.id.radioButton_Large).isChecked){
                    val order = PizzaOrder(subTotal, numToppings, size, "PLACEHOLDER TYPE")
                    myIntent.putExtra("order", order)
                    orderActivityLauncher.launch(myIntent)

                }
                else
                    Toast.makeText(this, "Please select a size for your pizza", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Please select a type for your pizza", Toast.LENGTH_SHORT).show()
        }

    }

    fun pizzaTypeRadioClick(view: View){
        val imageIdOfSelection = when (view.id){
            R.id.radioButton_Pepperoni -> R.drawable.pepperoni
            R.id.radioButton_Bbq -> R.drawable.bbq_chicken
            R.id.radioButton_Margherita -> R.drawable.margherita
            R.id.radioButton_Hawaiian -> R.drawable.hawaiian
            else -> R.drawable.pizza_crust
        }

        findViewById<ImageView>(R.id.imageView_Pizza).setImageResource(imageIdOfSelection)
    }

    fun pizzaSizeRadioClick(view: View){
        size = when (view.id){
            R.id.radioButton_Small -> "Small"
            R.id.radioButton_Medium -> "Medium"
            R.id.radioButton_Large -> "Large"
            else -> ""
        }

        updateSubtotal()
    }

    fun pizzaToppingsCheck(view: View){
        numToppings = 0
        if(findViewById<CheckBox>(R.id.checkBox_Tomatoes).isChecked)
            numToppings++
        if(findViewById<CheckBox>(R.id.checkBox_Olives).isChecked)
            numToppings++
        if(findViewById<CheckBox>(R.id.checkBox_Onions).isChecked)
            numToppings++
        if(findViewById<CheckBox>(R.id.checkBox_Spinach).isChecked)
            numToppings++
        if(findViewById<CheckBox>(R.id.checkBox_Mushrooms).isChecked)
            numToppings++
        if(findViewById<CheckBox>(R.id.checkBox_Broccoli).isChecked)
            numToppings++

        updateSubtotal()
    }

    private fun updateSubtotal(){
        // var to determine which toppings price we are using
        var toppingsMultiplier = 0.00
        // set subTotal to the base price based on size before adding toppings
        when (size){
            "Small" -> {
                subTotal = smallPrice
                toppingsMultiplier = smallTopping
            }
            "Medium" -> {
                subTotal = mediumPrice
                toppingsMultiplier = medTopping
            }
            "Large" -> {
                subTotal = largePrice
                toppingsMultiplier = largeTopping
            }
            else -> {
                subTotal = 0.00
            }
        }
        subTotal += (numToppings * toppingsMultiplier)

        findViewById<TextView>(R.id.textView_SubTotal).text = "Subtotal: $$subTotal"
    }

    fun reset(view: View){

    }

    /*
    fun checkout(view:View){
        val myIntent = Intent(this, PizzaOrderActivity::class.java)

        // if a type has been chosen
        if(findViewById<RadioButton>(R.id.radioButton_Pepperoni).isChecked ||
            findViewById<RadioButton>(R.id.radioButton_Bbq).isChecked ||
            findViewById<RadioButton>(R.id.radioButton_Margherita).isChecked ||
            findViewById<RadioButton>(R.id.radioButton_Hawaiian).isChecked){
            // if a size has been chosen
            if(findViewById<RadioButton>(R.id.radioButton_Small).isChecked ||
                findViewById<RadioButton>(R.id.radioButton_Medium).isChecked ||
                findViewById<RadioButton>(R.id.radioButton_Large).isChecked){
                val order = PizzaOrder(subTotal, numToppings, size, "PLACEHOLDER TYPE")
                myIntent.putExtra("order", order)
                orderActivityLauncher.launch(myIntent)

            }
            else
                Toast.makeText(this, "Please select a size for your pizza", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "Please select a type for your pizza", Toast.LENGTH_SHORT).show()
    }
     */
}