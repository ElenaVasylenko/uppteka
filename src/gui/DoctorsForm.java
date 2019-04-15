package gui;

import com.company.DemoLogic;
import model.Doctor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DoctorsForm implements View {
    private JPanel mainPanel;
    private JList list;
    private JButton seeRecipesButton;
    private JButton addDoctorButton;
    private JButton returnButton;

    public DoctorsForm(MainFrame mainFrame) {

        // set up list with demo data
        setUpList(mainFrame.getDemoLogic());

        seeRecipesButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new RecipesForm(mainFrame, ((Doctor) list.getSelectedValue()).getWrittenRecipes()));
        });

        returnButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new MainMenu(mainFrame));
        });

        addDoctorButton.addActionListener((ActionEvent e) -> {
            new AddDoctorDialog(mainFrame, this).setVisible(true);
        });
    }

    private void setUpList(DemoLogic demoLogic) {
        list.setListData(demoLogic.getDoctorsArr());
        list.setSelectedIndex(0);
    }

    public JPanel getView() {
        return mainPanel;
    }

    public String getTitle() {
        return "Лікарі";
    }

}
