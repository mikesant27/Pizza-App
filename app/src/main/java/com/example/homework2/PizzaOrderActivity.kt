package com.example.homework2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BundleCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class PizzaOrderActivity : AppCompatActivity() {
    private var quantity = 1
    private var delivery = false
    private var deliveryFee = 2.00
    private var subTotal = 0.00
    private var taxRate = 0.0635
    private var tip = 0 // percentage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pizza_order)

        val bundle = intent.extras
        if(bundle != null){
            val order = BundleCompat.getSerializable(bundle, "order", PizzaOrder::class.java)
            if(order != null){
                val size = order.size
                val numToppings = order.numToppings
                val imageIdOfSelection = order.type
                subTotal = order.subTotal

                findViewById<TextView>(R.id.textView_SubTotalValue).text = "$${String.format("%.2f", subTotal)}"

                val type = when (imageIdOfSelection){
                    R.drawable.pepperoni -> "Pepperoni"
                    R.drawable.bbq_chicken -> "BBQ Chicken"
                    R.drawable.margherita -> "Margherita"
                    R.drawable.hawaiian -> "Hawaiian"
                    else -> "Crust"
                }

                // Populate labels with data from selection
                findViewById<ImageView>(R.id.imageView_Pizza2).setImageResource(imageIdOfSelection)
                findViewById<TextView>(R.id.textView_ShowType).text = type
                findViewById<TextView>(R.id.textView_ShowSize).text = size
                findViewById<TextView>(R.id.textView_Quantity).text = "$quantity"
                if(numToppings == 1)
                    findViewById<TextView>(R.id.textView_ShowToppings).text = "$numToppings Topping"
                else
                    findViewById<TextView>(R.id.textView_ShowToppings).text = "$numToppings Toppings"

                updateSummary()
            }
        }
        findViewById<SeekBar>(R.id.seekBar_Tip).setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tip = progress
                findViewById<TextView>(R.id.textView_TipPercent).text = "$progress%"
                updateSummary()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

    }

    fun delivery(view: View){
        if (view is Switch){
            if(view.isChecked)
                delivery = true
            else
                delivery = false
        }
        updateSummary()
    }

    fun updateQuantity(view: View){
        if (view is Button){
            if(view.id == R.id.button_Minus) {
                if (quantity > 1)
                    quantity--
            }
            else if (view.id == R.id.button_Plus)
                quantity++
        }
        findViewById<TextView>(R.id.textView_Quantity).text = "$quantity"
        updateSummary()
    }

    fun editPizza(view: View){
        finish()
    }

    private fun updateSummary(){
        // displays subttotal
        findViewById<TextView>(R.id.textView_SubTotalValue).text = "\$${String.format("%.2f", subTotal * quantity)}"

        // determines if user wants delivery and outputs the value to the summary
        var deliveryMessage = ""
        var deliveryValue = 0.00
        if(delivery) {
            deliveryMessage = "Yes ($2.00)"
            deliveryValue = deliveryFee
        }
        else
            deliveryMessage = "No ($0.00)"
        findViewById<TextView>(R.id.textView_DeliverFeeValue).text = deliveryMessage

        // calculates and displays tax
        val tax = ((subTotal * quantity)+ deliveryValue) * taxRate
        findViewById<TextView>(R.id.textView_TaxValue).text = "${String.format("%.2f", tax)}"

        // calculates and displays tip
        val tipAmount = ((subTotal * quantity) + deliveryValue + tax) * (tip / 100.00)
        findViewById<TextView>(R.id.textView_TipValue).text = "${String.format("%.2f", tipAmount)}"

        // calculates and total
        val total =  (subTotal * quantity) + deliveryValue + tax + tipAmount
        findViewById<TextView>(R.id.textView_TotalValue).text = "${String.format("%.2f", total)}"
    }

    fun order(view: View){
        val myIntent = Intent()

        val total = findViewById<TextView>(R.id.textView_TotalValue).text
        myIntent.putExtra("total",total)
        setResult(Activity.RESULT_OK, myIntent)
        finish()
    }
}