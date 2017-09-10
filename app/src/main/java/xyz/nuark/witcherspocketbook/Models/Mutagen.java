package xyz.nuark.witcherspocketbook.Models;

import java.util.List;

public class Mutagen {
    private final int id;

    private final String usage;

    private final String name;

    private final String effect;

    private final String obtain;

    private final String image;

    private final String category;

    private final int basePrice;

    private final List<Creature> creature;

    private final List<Used> used;

    public Mutagen(int id, String usage, String name, String effect, String obtain, String image,
                   String category, int basePrice, List<Creature> creature, List<Used> used) {
        this.id = id;
        this.usage = usage;
        this.name = name;
        this.effect = effect;
        this.obtain = obtain;
        this.image = image;
        this.category = category;
        this.basePrice = basePrice;
        this.creature = creature;
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public String getUsage() {
        return usage;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public String getObtain() {
        return obtain;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public List<Creature> getCreature() {
        return creature;
    }

    public List<Used> getUsed() {
        return used;
    }

    public static class Creature {
        private final String variations;

        private final String imageCreature;

        private final String typeI;

        private final int id;

        public Creature(String variations, String imageCreature, String typeI, int id) {
            this.variations = variations;
            this.imageCreature = imageCreature;
            this.typeI = typeI;
            this.id = id;
        }

        public String getVariations() {
            return variations;
        }

        public String getImageCreature() {
            return imageCreature;
        }

        public String getTypeI() {
            return typeI;
        }

        public int getId() {
            return id;
        }
    }

    public static class Used {
        private final String used;

        private final String imageUsed;

        private final String typeI;

        private final int id;

        public Used(String used, String imageUsed, String typeI, int id) {
            this.used = used;
            this.imageUsed = imageUsed;
            this.typeI = typeI;
            this.id = id;
        }

        public String getUsed() {
            return used;
        }

        public String getImageUsed() {
            return imageUsed;
        }

        public String getTypeI() {
            return typeI;
        }

        public int getId() {
            return id;
        }
    }
}
