package model;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String passport;
    private String surname;
    private String birthday;
    private List<Recipe> writtenRecipes;

    public Patient(String passport, String surname, String birthday) {
        this.passport = passport;
        this.surname = surname;
        this.birthday = birthday;

        writtenRecipes = new ArrayList<>();

    }

    public String getPassport() {
        return passport;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<Recipe> getWrittenRecipes() {
        return writtenRecipes;
    }

    public void setWrittenRecipes(List<Recipe> writtenRecipes) {
        this.writtenRecipes = writtenRecipes;
    }

    public String toString() {
        return passport + ", " + surname + ", " + birthday;
    }
}
