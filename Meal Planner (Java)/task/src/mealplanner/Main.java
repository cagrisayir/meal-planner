package mealplanner;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var planner = new Planner();
        planner.menu();
    }
}