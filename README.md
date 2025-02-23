# Homework 2: Pizza Ordering App
Michael Santoro
CS 414-0

## Description: 

### Selecting a Pizza
On the selection screen, the user can customize their pizza, including type, size, and toppings. 

#### Choosing a Pizza Type
The user can select 4 different types of pizza using Radio Buttons:
- Pepperoni
- BBQ Chicken
- Margherita
- Hawaiian

An Image View to the left of the type selection displays a picture of the selected pizza. When no pizza type is selected, a pizza crust picture is displayed.

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

The prices for each size of topping are stored in gloval variables *smallTopping*, *mediumTopping*, and largeTopping*

#### Resetting the selection
The rest button resets the current pizza selection. It does this by first looping through an array of the checkboxes, and sets their isChecked value to false. Next it runs the ***clearCheck()*** function for both radio groups for type and size. Then it changes the Image View back to the pizza crust. Lastly, it resets the *subtotal* back to 0 and *size* back to "".
#### Checking out


### Ordering a Pizza
