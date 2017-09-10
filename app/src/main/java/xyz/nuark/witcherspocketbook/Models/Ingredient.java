package xyz.nuark.witcherspocketbook.Models;

import java.util.List;

public class Ingredient {
    private final int id;

    private final String name;

    private final String type;

    private final String image;

    private final List<DeconstructedInto> deconstructedInto;

    private final List<DeconstructedFrom> deconstructedFrom;

    private final List<Used> used;

    public Ingredient(int id, String name, String type, String image,
                      List<DeconstructedInto> deconstructedInto, List<DeconstructedFrom> deconstructedFrom,
                      List<Used> used) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.deconstructedInto = deconstructedInto;
        this.deconstructedFrom = deconstructedFrom;
        this.used = used;
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

    public List<DeconstructedInto> getDeconstructedInto() {
        return deconstructedInto;
    }

    public List<DeconstructedFrom> getDeconstructedFrom() {
        return deconstructedFrom;
    }

    public List<Used> getUsed() {
        return used;
    }

    public static class DeconstructedInto {
        private final String nr;

        private final String ingedient;

        private final String imageIngedient;

        private final String typeI;

        private final int id;

        public DeconstructedInto(String nr, String ingedient, String imageIngedient, String typeI,
                                 int id) {
            this.nr = nr;
            this.ingedient = ingedient;
            this.imageIngedient = imageIngedient;
            this.typeI = typeI;
            this.id = id;
        }

        public String getNr() {
            return nr;
        }

        public String getIngedient() {
            return ingedient;
        }

        public String getImageIngedient() {
            return imageIngedient;
        }

        public String getTypeI() {
            return typeI;
        }

        public int getId() {
            return id;
        }
    }

    public static class DeconstructedFrom {
        private final String ingedient;

        private final String imageIngedient;

        private final String typeI;

        private final int id;

        public DeconstructedFrom(String ingedient, String imageIngedient, String typeI, int id) {
            this.ingedient = ingedient;
            this.imageIngedient = imageIngedient;
            this.typeI = typeI;
            this.id = id;
        }

        public String getIngedient() {
            return ingedient;
        }

        public String getImageIngedient() {
            return imageIngedient;
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
