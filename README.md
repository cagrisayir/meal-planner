# Meal Planner
Meal planner project with Java and MYSQL

# Stage 1: Add Meals 
Description
Let's start with something simple. Write a program that can store meals and their properties. Prompt users about the category of a meal (breakfast, lunch, or dinner), name of a meal, and necessary ingredients. The program should print that information with the meal properties in the correct format. In this stage, you don't need to validate user input.

Objectives
To complete this stage, your program should:

Ask about the meal category with the following message: Which meal do you want to add (breakfast, lunch, dinner)?;
Ask about the name of the meal with the message Input the meal's name:;
Inquire about the necessary ingredients with the message Input the ingredients:. The input contains ingredients in one line separated by commas. The output displays each ingredient on a new line (see Examples);
Print all the information about the meal in the following format:
Category: category
Name: meal's name
Ingredients:
ingredient 1
ingredient 2
ingredient 3
Print the message that the meal is saved successfully: The meal has been added!.
### Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

#### Example 1: standard execution — lunch
```
Which meal do you want to add (breakfast, lunch, dinner)?
> lunch
Input the meal's name:
> salad
Input the ingredients:
> lettuce,tomato,onion,cheese,olives

Category: lunch
Name: salad
Ingredients:
lettuce
tomato
onion
cheese
olives
The meal has been added!
```

#### Example 2: standard execution — breakfast
```
Which meal do you want to add (breakfast, lunch, dinner)?
> breakfast
Input the meal's name:
> oatmeal
Input the ingredients:
> oats,milk,banana,peanut butter

Category: breakfast
Name: oatmeal
Ingredients:
oats
milk
banana
peanut butter
The meal has been added!
```

# Stage 2; Create a menu
One meal is not going to get you far! Let's create the main menu to add several meals and display their properties. For this, we need to add a few commands:

* Add starts the meal input process and prompts users for the meal properties;
* Show prints the list of saved meals with their names, categories, and ingredients;
* After executing add or show, the program should ask what users want to do next;
* The program must run until users input exit — the command that terminates the program.
* In this stage, your program should also check the user input. What if users enter something wrong?
  

If the input meal category is something other than breakfast, lunch, or dinner, print Wrong meal category! Choose from: breakfast, lunch, dinner. and prompt users for input;


If the meal's name or ingredients have a wrong format (for example, there are numbers or non-alphabet characters; ingredients are blank, and so on), print Wrong format. Use letters only! and prompt users for input. Meal's name or ingredients containing several words like "peanut butter" should not be matched as wrong format.

#### Example 1: standart execution
```
What would you like to do (add, show, exit)?
> add
Which meal do you want to add (breakfast, lunch, dinner)?
> lunch
Input the meal's name:
> salad
Input the ingredients:
> lettuce, tomato, onion, cheese, olives
The meal has been added!
What would you like to do (add, show, exit)?
> add
Which meal do you want to add (breakfast, lunch, dinner)?
> breakfast
Input the meal's name:
> oatmeal
Input the ingredients:
> oats, milk, banana, peanut butter
The meal has been added!
What would you like to do (add, show, exit)?
> show

Category: lunch
Name: salad
Ingredients:
lettuce
tomato
onion
cheese
olives

Category: breakfast
Name: oatmeal
Ingredients:
oats
milk
banana
peanut butter

What would you like to do (add, show, exit)?
> exit
Bye!
```

#### Example 2: Invalid input format
```
What would you like to do (add, show, exit)?
> show
No meals saved. Add a meal first.
What would you like to do (add, show, exit)?
> new meal
What would you like to do (add, show, exit)?
> meal
What would you like to do (add, show, exit)?
> add
Which meal do you want to add (breakfast, lunch, dinner)?
> dessert
Wrong meal category! Choose from: breakfast, lunch, dinner.
> lunch
Input the meal's name:
> burger1
Wrong format. Use letters only!
>
Wrong format. Use letters only!
> soup
Input the ingredients:
> carrots, ginger, coconut milk, 123
Wrong format. Use letters only!
> carrots, ginger, coconut milk, curry
The meal has been added!
What would you like to do (add, show, exit)?
> exit
Bye!
```
