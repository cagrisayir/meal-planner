package mealplanner;

import java.util.ArrayList;

public class Meals {
    private String name;
    private ArrayList<String> ingredients;

    public Meals() {
        ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingt) {
        ingredients.add(ingt);
    }

    public void setIngredients(String[] ingredients) {
        for (var i : ingredients) {
            addIngredient(i);
        }
    }

    public String getType() {
        return "MEAL";
    }
}
