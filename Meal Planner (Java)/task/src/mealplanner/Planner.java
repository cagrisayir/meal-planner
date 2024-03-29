package mealplanner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Planner {
    static Scanner scanner = new Scanner(System.in);

    public void menu() throws SQLException {
        var db = new DatabaseConnect();
        var connection = db.connect();
        while (true) {
            System.out.println("What would you like to do (add, show, exit)?");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (input.equals("add")) {
                Meals meal = mealChecker();
                mealAdder(meal);
                db.insertData(connection, meal);
                System.out.println("The meal has been added!");
            } else if (input.equals("show")) {
                String category = categoryChecker();
                db.printData(connection, category);
            }
        }

        db.closeConnection(connection);
    }

    public void mealAdder(Meals meal) {
        meal.setName(takeMealName());
        meal.setIngredients(ingredientsAdder());
    }

    public String[] ingredientsAdder() {
        System.out.println("Input the ingredients:");
        String[] ingresArray;
        while (true) {
            String ingres = scanner.nextLine();
            ingresArray = ingres.split(",");
            ArrayList<Integer> checkArray = new ArrayList<>();
            for (var ing : ingresArray) {
                if (checkFormat(ing.trim()))
                    checkArray.add(1);
            }
            if (checkArray.isEmpty()) break;
            System.out.println("Wrong format. Use letters only!");
        }
        return ingresArray;
    }

    public boolean checkFormat(String str) {
        return !str.matches("^[a-zA-Z ]*$") || str.trim().isEmpty();
    }

    public String takeMealName() {
        System.out.println("Input the meal's name:");
        String mealName;
        while (true) {
            mealName = scanner.nextLine();
            if (!checkFormat(mealName))
                break;
            System.out.println("Wrong format. Use letters only!");
        }
        return mealName;
    }

    public Meals mealChecker() {
        Meals meal;
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");

        while (true) {
            String mealStr = scanner.nextLine();
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

        return meal;
    }

    private String categoryChecker() {
        System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
        String userInput;
        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("breakfast") || userInput.equals("lunch") || userInput.equals("dinner")) {
                break;
            } else {
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            }
        }
        return userInput;
    }
}
