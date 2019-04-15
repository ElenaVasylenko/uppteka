package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.company.DemoLogic;
import model.Doctor;

public class AddDoctorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField specialtyField;
    private JTextField experienceField;

    public AddDoctorDialog(MainFrame mainFrame, View currentView) {
        setTitle("Додати лікаря");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        setMinimumSize(getPreferredSize());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(mainFrame, currentView);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK(MainFrame mainFrame, View currentView) {
        // add your code here
        Doctor doctor = new Doctor(DemoLogic.doctorId, nameField.getText(), specialtyField.getText(), Integer.parseInt(experienceField.getText()));

        mainFrame.getDemoLogic().addDoctor(doctor);
        mainFrame.changeView(currentView, new DoctorsForm(mainFrame));

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
