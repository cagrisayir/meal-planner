package mealplanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Meals meal;
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        String mealStr = scanner.nextLine();

        while (true) {
            if (mealStr.equalsIgnoreCase("breakfast")) {
                meal = new Breakfast();
                break;
            } else if (mealStr.equalsIgnoreCase("lunch")) {
                meal = new Lunch();
                break;
            } else if (mealStr.equalsIgnoreCase("dinner")) {
                meal = new Dinner();
                break;
            } else
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
        }

        System.out.println("Input the meal's name:");
        meal.setName(scanner.nextLine());

        System.out.println("Input the ingredients:");
        String ingres = scanner.nextLine();
        String[] ingresArray = ingres.split(",");
        meal.setIngredients(ingresArray);

        printMeal(meal);

    }

    public static void printMeal(Meals meal) {
        System.out.println("Category: " + meal.getType());
        System.out.println("Name: " + meal.getName());
        System.out.println("Ingredients:");
        for (var ing : meal.getIngredients()) {
            System.out.println(ing.trim());
        }
        System.out.println("The meal has been added!");
    }
}