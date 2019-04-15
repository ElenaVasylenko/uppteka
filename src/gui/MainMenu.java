package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu implements View {

    private JPanel mainPanel;
    private JButton patientMenuButton;
    private JButton doctorMenuButton;

    public MainMenu(MainFrame mainFrame) {

        patientMenuButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new PatientsForm(mainFrame));
        });

        doctorMenuButton.addActionListener((ActionEvent e) -> {
            mainFrame.changeView(this, new DoctorsForm(mainFrame));
        });
    }

    public JPanel getView() {
        return mainPanel;
    }

    public String getTitle() {
        return "Головне меню";
    }

}
