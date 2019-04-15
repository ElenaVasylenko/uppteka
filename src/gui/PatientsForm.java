package gui;

import com.company.DemoLogic;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PatientsForm implements View {

    private JPanel mainPanel;
    private JList list;
    private JButton seeRecipesButton;
    private JButton writeRecipeButton;
    private JButton returnButton;

    public PatientsForm(MainFrame mainFrame) {

        // set up list with demo data
        setUpList(mainFrame.getDemoLogic());

        returnButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new MainMenu(mainFrame));
        });

        seeRecipesButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new RecipesForm(mainFrame, ((Patient) list.getSelectedValue()).getWrittenRecipes()));
        });

        writeRecipeButton.addActionListener((ActionEvent e) -> {
            new WriteRecipeDialog(mainFrame, this, ((Patient) list.getSelectedValue()).getSurname()).setVisible(true);
        });
    }

    private void setUpList(DemoLogic demoLogic) {
        list.setListData(demoLogic.getPatientsArr());
        list.setSelectedIndex(0);
    }

    public JPanel getView() {
        return mainPanel;
    }

    public String getTitle() {
        return "Пацієнти";
    }

}
