package xyz.nuark.witcherspocketbook.Models;

import java.util.List;

public class Creature {
    private final int id;

    private final String name;

    private final String aClass;

    private final String image;

    private final List<Occurrence> occurrence;

    private final List<Susceptibility> susceptibility;

    private final List<Variations> variations;

    private final List<Drop> drop;

    public Creature(int id, String name, String aClass, String image, List<Occurrence> occurrence,
                    List<Susceptibility> susceptibility, List<Variations> variations, List<Drop> drop) {
        this.id = id;
        this.name = name;
        this.aClass = aClass;
        this.image = image;
        this.occurrence = occurrence;
        this.susceptibility = susceptibility;
        this.variations = variations;
        this.drop = drop;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatureClass() {
        return aClass;
    }

    public String getImage() {
        return image;
    }

    public List<Occurrence> getOccurrence() {
        return occurrence;
    }

    public List<Susceptibility> getSusceptibility() {
        return susceptibility;
    }

    public List<Variations> getVariations() {
        return variations;
    }

    public List<Drop> getDrop() {
        return drop;
    }

    public static class Occurrence {
        private final String occurrence;

        public Occurrence(String occurrence) {
            this.occurrence = occurrence;
        }

        public String getOccurrence() {
            return occurrence;
        }
    }

    public static class Susceptibility {
        private final String susceptibility;

        private final String imageSusceptibility;

        private final String typeI;

        private final int id;

        public Susceptibility(String susceptibility, String imageSusceptibility, String typeI,
                              int id) {
            this.susceptibility = susceptibility;
            this.imageSusceptibility = imageSusceptibility;
            this.typeI = typeI;
            this.id = id;
        }

        public String getSusceptibility() {
            return susceptibility;
        }

        public String getImageSusceptibility() {
            return imageSusceptibility;
        }

        public String getTypeI() {
            return typeI;
        }

        public int getId() {
            return id;
        }
    }

    public static class Variations {
        private final String variations;

        private final String imageCreature;

        private final String typeI;

        private final int id;

        public Variations(String variations, String imageCreature, String typeI, int id) {
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

    public static class Drop {
        private final String drop;

        private final String imageDrop;

        private final String typeI;

        private final int id;

        public Drop(String drop, String imageDrop, String typeI, int id) {
            this.drop = drop;
            this.imageDrop = imageDrop;
            this.typeI = typeI;
            this.id = id;
        }

        public String getDrop() {
            return drop;
        }

        public String getImageDrop() {
            return imageDrop;
        }

        public String getTypeI() {
            return typeI;
        }

        public int getId() {
            return id;
        }
    }
}
