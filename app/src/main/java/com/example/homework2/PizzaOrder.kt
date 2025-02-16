package com.example.homework2

import java.io.Serializable

data class PizzaOrder (
    val subTotal: Double,
    val numToppings: Int,
    val size: String,
    val type: Int
): Serializable