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

Lastly, the image of the Image View is updated using the ***setImageResource()*** function, passing the variable *imageIdOfSelection* that is changed when the user selects a type.

#### Checking out
When the user presses the check out button, we first check if a type and size have been chozen, if one or both have not be chozen, a Toast message will appear to tell the user to select a type of size, depending on which one they did not select. 

To assit with the passing of information to the Order Activity, as ***PizzaOrder*** data class was created. the ***PizzaOrder*** class stores 4 variables, *subTotal*, *numToppings*, *size*, and *type*. When the user presses the check out button, an instance of the ***PizzaOrder* class is passed to the Intent using the ***putExtra()*** function, passing the *subTotal*, *numToppings*, *size*, and *imageIdOfSelection*

### Ordering a Pizza
After the App has switched to the Order Activity, we need to pull the values from the Intent. We do that using ***BundleCompat.getSerializable()***. Since the *type* value from the order is just the id of the image, we can directly set the image view to that, and use a when case to determine the value of a variable named *type*. We can also use these values to display the pizza summary at the top of the screen.

There are some global variables in this Activity that are important in calculating the total of the transation.
- *quantity*: Number of pizzas the user is buying; set to 1 by default
- *delivery*: Whether the user wants deliver; set to false by default
- *deliverFee*: The price for deliver; set to 2.00 by default
- *subTotal*: the subTotal of the transaction before delivery, tip, and tax
- *taxRate*: The tax rate in decimat; set to 0.0635 by default for 6.35% tax
- *tip*: The percentage tip the user decides; set to 0 by default

### Determining the tip
The *tip* percentage is determined by a seek bar, where whenever the progress of the seek bar is changed, the *tip* is set to *progress* so that it can be used later.

### Determining delivery
To determine whether or not there is a delivery fee, an if statement is used. If *view.checked* is true, then we set *delivery* to true. Otherwise, we set *delivery* to false

### Increasing and Decreasing the Quantity
To update the quanity, we have 2 Buttons, plus and minus, and a Text View label. When the user presses plus, *quanitity* goes up, and when the user presses minus, *quantity* goes down. The value of *quantity* cannot be less than 1, so pressing the minus button when *quantity* = 1 does nothing. 

### Updating the Order Summary
Whenever the *quantity*, *delivery*, or *tip* is changed, we need to update the Order Summary. The order summary is displayed as 3 "columns" using Linear Layouts. The first column consists of labels to show the user which value is which. The second column displays the actual values of the subtotal, delivery, tip, tax, and total price. The last column has a switch for the delivery, label to show the tip percentage, and the seek bar to edit the tip amount. In retrospect, I think that a table layout would have been easier to implement, as I had to use an invisible text view and contraints to make everything line up.

When ***updateSummary()*** is called, these values are updated. 

The *subtTotal* is calculated by doing *subTotal* * *quantiy*. 

We determine if a delivery fee needs to be applied by delaring a value called *deliveryValue*. If *delivery* is true, we set *deliveryValue* to *deliveryFee*. I did this so that the calculation for *total* can be one equation ,and doesnt have to be 2 different equations based on if there is a delivery or not. 

*tax* is calculated by doing the following equation:
- ((*subtotal* * *quantity*) + *deliveryValue*) * *taxRate*
