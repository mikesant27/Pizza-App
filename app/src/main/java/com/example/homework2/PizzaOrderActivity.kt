package com.example.homework2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BundleCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PizzaOrderActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pizza_order)

        val bundle = intent.extras
        if(bundle != null){
            val order = BundleCompat.getSerializable(bundle, "order", PizzaOrder::class.java)
            if(order != null){
                val (subTotal, numToppings, size, type) = order

                findViewById<TextView>(R.id.textView_SubTotalValue).text = "$subTotal"

                val imageIdOfSelection = when (type){
                    "Pepperoni" -> R.drawable.pepperoni
                    "BBQ" -> R.drawable.bbq_chicken
                    "Margherita" -> R.drawable.margherita
                    "Hawaiian" -> R.drawable.hawaiian
                    else -> R.drawable.pizza_crust
                }
                findViewById<ImageView>(R.id.imageView_Pizza2).setImageResource(imageIdOfSelection)
                findViewById<TextView>(R.id.textView_ShowType).text = type
                findViewById<TextView>(R.id.textView_ShowSize).text = size
                if(numToppings == 1)
                    findViewById<TextView>(R.id.textView_ShowToppings).text = "$numToppings Topping"
                else
                    findViewById<TextView>(R.id.textView_ShowToppings).text = "$numToppings Toppings"

            }
        }
    }
}