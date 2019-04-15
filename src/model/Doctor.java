package model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private int id;
    private String surname;
    private String specialty;
    private int experience;
    private List<Recipe> writtenRecipes;

    public Doctor(int id, String surname, String specialty, int experience) {
        this.id = id;
        this.surname = surname;
        this.specialty = specialty;
        this.experience = experience;

        writtenRecipes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return surname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getExperience() {
        return experience;
    }

    public List<Recipe> getWrittenRecipes() {
        return writtenRecipes;
    }

    public void setWrittenRecipes(List<Recipe> writtenRecipes) {
        this.writtenRecipes = writtenRecipes;
    }

    public String toString() {
        return surname + ", " + specialty + ", " + experience;
    }
}
