package com.company;

import model.*;

import java.util.Arrays;

public class DemoLogic {

    public static int doctorId = 2;
    public static int recipeId = 3;

    // ALL DRUGSTORES
    private Drugstore[] drugstoresArr = {
            new Drugstore(0, "Аптека низьких цін", "Сковороди, 2"),
            new Drugstore(1, "Аптека Бджілка", "Хрещатик, 79")
    };

    private Drug[] drugsArr;
    private Recipe[] recipesArr;
    private Doctor[] doctorsArr;
    private Patient[] patientsArr;

    public DemoLogic() {
        drugsArr = prepareDrugs();
        recipesArr = prepareRecipes();
        doctorsArr = prepareDoctors();
        patientsArr = preparePatients();
    }

    // ALL DRUGS
    public Drug[] prepareDrugs() {
        Drug[] arr = {
                new Drug(0, "Ібупрофен", "ВОЛЯ ТОВ", 1000),
                new Drug(1, "Парацетамол", "Дарниця", 1100),
                new Drug(2, "Аспірін", "ВОЛЯ ТОВ", 900),
                new Drug(3, "Йод", "Чернігівниця", 535.6)
        };

        arr[0].getDrugstoresSelling().add(new DrugInDrugstore(drugstoresArr[0], "18.10.2018", 20.5));

        arr[1].getDrugstoresSelling().add(new DrugInDrugstore(drugstoresArr[0], "18.10.2018", 30));
        arr[1].getDrugstoresSelling().add(new DrugInDrugstore(drugstoresArr[1], "18.10.2018", 32));

        arr[2].getDrugstoresSelling().add(new DrugInDrugstore(drugstoresArr[1], "18.10.2018", 12.95));
        arr[2].getDrugstoresSelling().add(new DrugInDrugstore(drugstoresArr[0], "18.10.2018", 12.4));

        arr[3].getDrugstoresSelling().add(new DrugInDrugstore(drugstoresArr[1], "18.10.2018", 9));

        return arr;
    }

    // ALL RECIPES
    public Recipe[] prepareRecipes() {
        Recipe[] arr = {
                new Recipe(0, "20.02.2019", "Вася", "Якийсь"),
                new Recipe(1, "13.01.2019", "Петя", "Побігайчик"),
                new Recipe(2, "31.12.2018", "Вася", "Побігайчик")
        };

        arr[0].getPrescribedDrugs().add(new DrugInRecipe(drugsArr[0], 2));
        arr[0].getPrescribedDrugs().add(new DrugInRecipe(drugsArr[1], 1));

        arr[1].getPrescribedDrugs().add(new DrugInRecipe(drugsArr[1], 1));
        arr[1].getPrescribedDrugs().add(new DrugInRecipe(drugsArr[2], 3));

        arr[2].getPrescribedDrugs().add(new DrugInRecipe(drugsArr[2], 1));
        arr[2].getPrescribedDrugs().add(new DrugInRecipe(drugsArr[3], 2));

        return arr;
    }

    // ALL DOCTORS
    public Doctor[] prepareDoctors() {
        Doctor[] arr = {
                new Doctor(0, "Вася", "Онколог", 2),
                new Doctor(1, "Петя", "Дерматолог", 3)
        };

        arr[0].getWrittenRecipes().add(recipesArr[0]);
        arr[0].getWrittenRecipes().add(recipesArr[2]);

        arr[1].getWrittenRecipes().add(recipesArr[1]);
        return arr;

        // DefaultListModel<>
    }

    // ALL PATIENTS
    public Patient[] preparePatients() {
        Patient[] arr = {
                new Patient("AS123456", "Якийсь", "11.12.1990"),
                new Patient("QW789012", "Побігайчик", "01.05.1965")
        };

        arr[0].getWrittenRecipes().add(recipesArr[0]);

        arr[1].getWrittenRecipes().add(recipesArr[1]);
        arr[1].getWrittenRecipes().add(recipesArr[2]);

        return arr;
    }

    public void addDoctor(Doctor doctor) {
        doctorsArr = Arrays.copyOf(doctorsArr, doctorsArr.length + 1);
        doctorsArr[doctorsArr.length - 1] = doctor;
        doctorId++;
    }

    public void addRecipe(Recipe recipe) {
        recipesArr = Arrays.copyOf(recipesArr, recipesArr.length + 1);
        recipesArr[recipesArr.length - 1] = recipe;
        patientsArr[0].getWrittenRecipes().add(recipesArr[recipesArr.length - 1]);
        doctorsArr[0].getWrittenRecipes().add(recipesArr[recipesArr.length - 1]);
        recipeId++;
    }

    public Drug[] getDrugsArr() {
        return drugsArr;
    }

    public Drugstore[] getDrugstoresArr() {
        return drugstoresArr;
    }

    public Recipe[] getRecipesArr() {
        return recipesArr;
    }

    public Doctor[] getDoctorsArr() {
        return doctorsArr;
    }

    public Patient[] getPatientsArr() {
        return patientsArr;
    }

    public void setDrugstoresArr(Drugstore[] drugstoresArr) {
        this.drugstoresArr = drugstoresArr;
    }

    public void setDrugsArr(Drug[] drugsArr) {
        this.drugsArr = drugsArr;
    }

    public void setRecipesArr(Recipe[] recipesArr) {
        this.recipesArr = recipesArr;
    }

    public void setDoctorsArr(Doctor[] doctorsArr) {
        this.doctorsArr = doctorsArr;
    }

    public void setPatientsArr(Patient[] patientsArr) {
        this.patientsArr = patientsArr;
    }
}
