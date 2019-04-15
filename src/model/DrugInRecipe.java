package model;

public class DrugInRecipe {

    private Drug drug;
    private int amount;

    public DrugInRecipe(Drug drug, int amount) {
        this.drug = drug;
        this.amount = amount;
    }

    public Drug getDrug() {
        return drug;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return drug.toString() + ", к-сть: " + amount;
    }
}
