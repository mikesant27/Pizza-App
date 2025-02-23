# Homework 2: Pizza Ordering App
Michael Santoro
CS 414-02

## Description: 

### Selecting a Pizza
On the selection screen, the user can customize their pizza, including type, size, and toppings. 

#### Choosing a Pizza Type
The user can select 4 different types of pizza using Radio Buttons:
- Pepperoni
- BBQ Chicken
- Margherita
- Hawaiian

An Image View to the left of the type selection displays a picture of the selected pizza. When no pizza type is selected, a pizza crust picture is displayed. The type of pizza is not stored explicitly, but is implied through the use of a variable named *imageIdOfSelection*. By default it is set to *R.drawable.pizza_crust* which is the blank pizza, but when the user selects a type, this variable is changed to *R.drawble.Selection*

#### Choosing a Pizza Size
The user can select their desired pizza size using Radio Buttons. Each size has its own price
- Small ($10.29)
- Medium ($12.59)
- Large ($14.89)

The prices for each size of pizza are stored in global variables *smallPrice*, *mediumPrice* and *largePrice*

#### Choosing extra toppings
The user can select and additional toppings using Check Boxes:
- Tomatoes
- Mushrooms
- Olives
- Onions
- Broccoli
- Spinach
The price per topping is determined on the size of the pizza:
- Small ($1.39/ea)
- Medium ($2.21/ea)
- Large ($2.29/ea)

The prices for each size of topping are stored in global variables *smallTopping*, *mediumTopping*, and *largeTopping*. Additionally, the number of toppings is stoped in a global variable named *numToppings*

#### Resetting the Selection
The rest button resets the current pizza selection. It does this by first looping through an array of the checkboxes, and sets their isChecked value to false. Next it runs the ***clearCheck()*** function for both radio groups for type and size. Then it changes the Image View back to the pizza crust. Lastly, it resets the *subtotal* back to 0, *size* back to "", and runs the ***updateSubtotal()*** function to display these changes to the user.

#### Updating the Subtotal 
Whenever the user makes a change to their selection, whether it by the pizza type, size, or toppings, we need to reflect the new subtotal to the user. This is handled by the ***updateSubtotal()*** function. 

First I declared a variabled called *toppingsMultiplier*, which is used to determine which toppings price we are using. Using a when statement using the *size* variable as the condition, I set the *subTotal* variable to the price of the size the user selected as the base price without toppings, then set *toppingsMultiplier* to the price of the toppings for that price. 

Ex. if *size* = "Small", *subTotal* = *smallPrice* and *toppingsMultiplier* = *smallTopping*

To get the Subtotal, we then have to add *numToppings* * *toppingsMultiplier* to the current subtotal. 

#### Checking out


### Ordering a Pizza
