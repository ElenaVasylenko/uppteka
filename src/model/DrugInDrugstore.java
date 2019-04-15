package model;

public class DrugInDrugstore {

    Drugstore drugstore;
    String date;
    double price;

    public DrugInDrugstore(Drugstore drugstore, String date, double price) {
        this.drugstore = drugstore;
        this.date = date;
        this.price = price;
    }

    public Drugstore getDrugstore() {
        return drugstore;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return drugstore.toString() + " - " + price + " грн. за упаковку";
    }
}
