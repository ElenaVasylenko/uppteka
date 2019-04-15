package model;

import java.util.ArrayList;
import java.util.List;

public class Drug {

    private int id;
    private String name;
    private String producer;
    private double packagePrice;
    private List<DrugInDrugstore> drugstoresSelling;

    public Drug(int id, String name, String producer, double packagePrice) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.packagePrice = packagePrice;

        drugstoresSelling = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public List<DrugInDrugstore> getDrugstoresSelling() {
        return drugstoresSelling;
    }

    public void setDrugstoresSelling(List<DrugInDrugstore> drugstoresSelling) {
        this.drugstoresSelling = drugstoresSelling;
    }

    public String toString() {
        return name + " - виробник: " + producer;
    }
}
