package xyz.nuark.witcherspocketbook.Models;

import java.util.List;

public class Item {
    private final int id;

    private final String name;

    private final String type;

    private final String image;

    private final String effect;

    private final String recipeLocation;

    private final List<Description> description;

    private final List<Ingredients> ingredients;

    public Item(int id, String name, String type, String image, String effect,
                String recipeLocation, List<Description> description, List<Ingredients> ingredients) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.effect = effect;
        this.recipeLocation = recipeLocation;
        this.description = description;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getEffect() {
        return effect;
    }

    public String getRecipeLocation() {
        return recipeLocation;
    }

    public List<Description> getDescription() {
        return description;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public static class Description {
        private final String description;

        public Description(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static class Ingredients {
        private final String nr;

        private final String ingedient;

        private final String imageIngredient;

        private final String typeI;

        private final int id;

        public Ingredients(String nr, String ingedient, String imageIngredient, String typeI,
                           int id) {
            this.nr = nr;
            this.ingedient = ingedient;
            this.imageIngredient = imageIngredient;
            this.typeI = typeI;
            this.id = id;
        }

        public String getNr() {
            return nr;
        }

        public String getIngedient() {
            return ingedient;
        }

        public String getImageIngredient() {
            return imageIngredient;
        }

        public String getTypeI() {
            return typeI;
        }

        public int getId() {
            return id;
        }
    }
}
