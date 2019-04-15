package db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;

public class DB {
    private Connection con;
    PreparedStatement statement;

    public DB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?" +
                    "user=root&password=root");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
    }

    public ResultSet runQuery(String qry) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
//			while(rs.next())
//			System.out.println(rs.getInt("id")+rs.getString("address"));
            // con.close();
            return rs;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return null;
    }

    // patients or doctors
    public ResultSet selectAllFromTable(String tablename) {
        try {
            String query = String.format("select * from %s", tablename);
            Statement ps = con.createStatement();
//            ps = con.prepareStatement("select * from patient");
            ResultSet rs = ps.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println("newId> " + e.getMessage());
        }
        return null;
    }

    public void addDoctor(Doctor d) throws SQLException {
        Statement ps = con.createStatement();
        String query = String.format("INSERT INTO `mydb`.`doctor` (`surname`, `speciality`, `experience`) VALUES ('%s', '%s', '%s')",
                d.getName(), d.getSpecialty(), d.getExperience());
        //System.out.println(query);
        ps.executeUpdate(query);
    }

    public java.sql.Date convertDateToSqlDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date sqlDate= null;
        try {
            Date utilDate = format.parse(date.toString());
            sqlDate = new java.sql.Date(utilDate.getTime());
            System.out.println(sqlDate);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

//    public void addRecipe(Recipe r) throws SQLException {
//        Statement ps = con.createStatement();
//        Statement ps2 = con.createStatement();
//        String query = String.format("INSERT INTO `mydb`.`recipe` (`date`, `patient_passport`, `doctor_id`) VALUES ('%s', '%s', '%s')",r.getDate(),
//                r.getPatient().getPassport(), r.getDoctor().getId());
//        ps.executeUpdate(query);
//
//        for (int i=0; i<r.getPrescribedDrugs().size(); i++){
//            String query2 = String.format("INSERT INTO `mydb`.`recipe_drug` (`recipe_id`, `drug_recipe_drug`, `amount`) VALUES ('%s', '%s', '%s')",
//            r.getId(), r.getPrescribedDrugs().get(i).getDrug().getId(), r.getPrescribedDrugs().get(i).getAmount());
//            ps2.executeUpdate(query2);
//        }
//    }

    public ArrayList<Drugstore> existenceInDrugstore(Drug drug) throws SQLException {
        Statement ps = con.createStatement();
        String query = String.format("select mydb.drugstore_drug.drugstore_id, \n" +
                "mydb.drugstore.name, mydb.drugstore.address\n" +
                "from mydb.drugstore_drug inner join mydb.drugstore \n" +
                "on mydb.drugstore.id= mydb.drugstore_drug.drugstore_id \n" +
                "where mydb.drugstore_drug.drug_drugstore_drug = %s", drug.getId());
        ResultSet rs = ps.executeQuery(query);
        Drugstore d ;
        ArrayList<Drugstore> drugstores = new ArrayList<>();
        while (rs.next()){
            d = new Drugstore(rs.getInt(1), rs.getString(2), rs.getString(3));
            drugstores.add(d);
        }
        return drugstores;
    }


//    public ArrayList<Recipe> getAllRecipes() throws SQLException {
//        Statement ps = con.createStatement();
//        Statement ps2 = con.createStatement();
//        String query1 = "SELECT mydb.recipe.id, mydb.recipe.date, mydb.recipe.doctor_id, mydb.doctor.surname,\n" +
//                "mydb.doctor.speciality, mydb.doctor.experience, mydb.recipe.patient_passport,\n" +
//                "mydb.patient.surname, mydb.patient.birthday\n" +
//                "FROM mydb.recipe \n" +
//                "inner join mydb.patient on mydb.recipe.patient_passport = mydb.patient.passport\n" +
//                "inner join mydb.doctor on mydb.doctor.id = mydb.recipe.doctor_id";
//        String query2 = "select mydb.recipe_drug.recipe_id, mydb.recipe_drug.drug_recipe_drug, mydb.drug.name,\n" +
//                "mydb.drug.package_price, mydb.drug.producer, mydb.recipe_drug.amount\n" +
//                "from mydb.recipe_drug \n" +
//                "inner join mydb.drug on mydb.drug.id = mydb.recipe_drug.drug_recipe_drug";
//
//        ResultSet rs = ps.executeQuery(query1);
//        ResultSet rs1 = ps2.executeQuery(query2);
//        Doctor d;
//        Patient p;
//        Drug drug;
//        DrugInRecipe dir;
//        ArrayList<Recipe> recipes = new ArrayList<>();
//        ArrayList<DrugInRecipe> dr;
//        ArrayList<DrugInRecipe> drugsInRec = new ArrayList<>();
//
//        while (rs.next()) {
//            Recipe r = new Recipe(rs.getInt(1), rs.getString(2), d = new Doctor(rs.getInt(3), rs.getString(4),
//                    rs.getString(5), rs.getInt(6)), p = new Patient(rs.getString(7), rs.getString(8),
//                    rs.getString(9)), dr = new ArrayList<DrugInRecipe>());
//            recipes.add(r);
//        }
//        while (rs1.next()) {
//            dir = new DrugInRecipe(rs1.getInt(1), drug = new Drug(rs1.getInt(2), rs1.getString(3),
//                    rs1.getString(5), rs1.getFloat(4)), rs1.getInt(6));
//            drugsInRec.add(dir);
//        }
//
//        System.out.println(drugsInRec);
//        ArrayList<DrugInRecipe> resDr ;
//        for (int i = recipes.size()-1; i >= 0; i--){
//            for (int k = 0; k < drugsInRec.size(); k++) {
//                System.out.println(recipes.get(i).getId() + "lel");
//                System.out.println(drugsInRec.get(k).getRecId());
//
//                if (recipes.get(i).getId() == drugsInRec.get(k).getRecId()){
//                    recipes.get(i).getPrescribedDrugs().add(drugsInRec.get(k));
//                    System.out.println(recipes.get(i));
//                }
//            }
//        }
//        System.out.println(recipes);
//
//        return recipes;
//    }

    public ArrayList<Patient> getPatientsFromRS(ResultSet rs) throws SQLException {
        ArrayList<Patient> arr = new ArrayList<Patient>();
        while (rs.next()){
            Patient p = new Patient(rs.getString("passport"), rs.getString("last_name"),
                    rs.getString("birthday"));
            arr.add(p);
        }
        return arr;
    };

    public ArrayList<Doctor> getDoctorsFromRS(ResultSet rs) throws SQLException {
        ArrayList<Doctor> arr = new ArrayList<Doctor>();
        while (rs.next()){
            Doctor p = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4));
            arr.add(p);
        }
        return arr;
    };

//    public ArrayList<Recipe> getRecipesFromRS(ResultSet rs) throws SQLException {
//        ArrayList<Recipe> arr = new ArrayList<Recipe>();
//        while (rs.next()){
//            Recipe p = new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3),
//                    rs.getInt(4));
//            arr.add(p);
//        }
//        return arr;
//    };




    public void getRecipesFromRS(ResultSet rs){

    }


}
