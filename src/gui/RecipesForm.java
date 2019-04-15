package gui;

import com.company.DemoLogic;
import model.Doctor;
import model.DrugInRecipe;
import model.Recipe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RecipesForm implements View {

    private JList list;
    private JButton seeDrugstoresButton;
    private JButton returnButton;
    private JPanel mainPanel;
    private JList drugsList;

    public RecipesForm(MainFrame mainFrame, List<Recipe> recipes) {

        // set up list with demo data
        setUpList(recipes);

        returnButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new MainMenu(mainFrame));
        });

        seeDrugstoresButton.addActionListener((ActionEvent e) -> {
            new DrugstoresDialog(((DrugInRecipe) drugsList.getSelectedValue()).getDrug().getDrugstoresSelling()).setVisible(true);
        });
    }

    private void setUpList(List<Recipe> recipes) {
        list.setListData(recipes.toArray());
        if (!recipes.isEmpty()) {
            list.setSelectedIndex(0);
        }

        drugsList.setListData(((Recipe) list.getSelectedValue()).getPrescribedDrugs().toArray());
        drugsList.setSelectedIndex(0);

        list.addListSelectionListener((ListSelectionEvent e) -> {
            drugsList.setListData(((Recipe) list.getSelectedValue()).getPrescribedDrugs().toArray());
        });
    }

    public JPanel getView() {
        return mainPanel;
    }

    public String getTitle() {
        return "Рецепти";
    }

}
