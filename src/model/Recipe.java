package model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private int id;
    private String date;
    private String doctorSurname;
    private String patientSurname;
    private List<DrugInRecipe> prescribedDrugs;

    public Recipe(int id, String date, String doctorSurname, String patientSurname) {
        this.id = id;
        this.date = date;
        this.doctorSurname = doctorSurname;
        this.patientSurname = patientSurname;

        prescribedDrugs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public List<DrugInRecipe> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(List<DrugInRecipe> prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }

    public String toString() {
        return date + " - виписав " + doctorSurname + " пацієнту " + patientSurname;
    }
}
