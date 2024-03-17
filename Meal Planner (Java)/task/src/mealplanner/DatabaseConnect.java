package mealplanner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnect {
    private Integer mealId = 0;
    private Integer ingId = 0;

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql:meal_db";
        String USER = "postgres";
        String PASSWORD = "postgres";
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        connection.setAutoCommit(true);

        return connection;
    }

    public void insertData(Connection connection, Meals meals) throws SQLException {
        int meal_id = mealId++;

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

    public void printData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from meals");
        ArrayList<String> mealName = new ArrayList<>();
        ArrayList<Integer> mealId = new ArrayList<>();
        ArrayList<String> mealCat = new ArrayList<>();
        while (rs.next()) {
            mealName.add(rs.getString("meal"));
            mealId.add(rs.getInt("mealId"));
            mealCat.add(rs.getString("category"));
        }

        Map<Integer, List<String>> mealsWithIng = new HashMap<>();
        for (var id : mealId) {
            List<String> ings = new ArrayList<>();
            ResultSet newrs = statement.executeQuery("SELECT * from ingredients WHERE meal_id=" + id.toString());
            while (newrs.next()) {
                ings.add(newrs.getString("ingredient"));
            }
            mealsWithIng.put(id, ings);
        }

        for (var i : mealId) {
            System.out.println("Category: " + mealCat.get(i));
            System.out.println("Name: " + mealName.get(i));
            System.out.println("Ingredients: ");
            var ings = mealsWithIng.get(i);
            for (var j : ings) {
                System.out.println(j);
            }
        }
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
