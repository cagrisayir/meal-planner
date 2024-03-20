package mealplanner;

import java.sql.*;
import java.util.*;

public class DatabaseConnect {
    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql:meals_db";
        String USER = "postgres";
        String PASSWORD = "1111";
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        connection.setAutoCommit(true);
        createTables(connection);
        return connection;
    }

    private void createTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS meals (" +
                "category VARCHAR(50)," +
                "meal VARCHAR(50)," +
                "meal_id INT" +
                ")");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS ingredients (" +
                "ingredient VARCHAR(50)," +
                "ingredient_id INT," +
                "meal_id INT" +
                ")");
    }

    public void insertData(Connection connection, Meals meals) throws SQLException {
        Random random = new Random();
        int meal_id = random.nextInt();
        int ingId = random.nextInt();

        String insertMeal = "INSERT INTO meals (category, meal, meal_id) VALUES (?, ?, ?)";
        String insertIngredients = "INSERT INTO ingredients (ingredient, ingredient_id, meal_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertMeal)) {
            preparedStatement.setString(1, meals.getType());
            preparedStatement.setString(2, meals.getName());
            preparedStatement.setInt(3, meal_id);

            preparedStatement.executeUpdate();
        }

        for (var ing : meals.getIngredients()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIngredients)) {
                preparedStatement.setString(1, ing);
                preparedStatement.setInt(2, ingId++);
                preparedStatement.setInt(3, meal_id);

                preparedStatement.executeUpdate();
            }
        }
    }

    public void printData(Connection connection, String category) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from meals");

        ArrayList<String> mealName = new ArrayList<>();
        ArrayList<Integer> mealId = new ArrayList<>();
        ArrayList<String> mealCat = new ArrayList<>();
        while (rs.next()) {
            mealName.add(rs.getString("meal"));
            mealId.add(rs.getInt("meal_id"));
            mealCat.add(rs.getString("category"));
        }
        if (mealCat.isEmpty()) {
            System.out.println("No meals found.");
            return;
        }
        Map<Integer, List<String>> mealsWithIng = new HashMap<>();

        if (!mealCat.contains(category)) {
            System.out.println("No meals found.");
            return;
        }

        for (var id : mealId) {
            List<String> ings = new ArrayList<>();
            ResultSet newrs = statement.executeQuery("SELECT * from ingredients WHERE meal_id=" + id.toString());
            while (newrs.next()) {
                ings.add(newrs.getString("ingredient"));
            }
            mealsWithIng.put(id, ings);
        }

        System.out.println("Category: " + category);
        System.out.println(" ");
        for (var i = 0; i < mealId.size(); i++) {
            if (category.equals(mealCat.get(i))) {
                System.out.println("Name: " + mealName.get(i));
                System.out.println("Ingredients: ");
                for (var j : mealsWithIng.get(mealId.get(i)))
                    System.out.println(j.trim());
                System.out.println("");
            }
        }
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
