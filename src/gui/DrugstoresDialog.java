package gui;

import model.DrugInDrugstore;
import model.Drugstore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DrugstoresDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList list;

    public DrugstoresDialog(List<DrugInDrugstore> drugstores) {

        // set up list
        setUpList(drugstores);

        setTitle("Наявні у аптеках");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        setMinimumSize(getPreferredSize());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        pack();
    }

    private void setUpList(List<DrugInDrugstore> drugstores) {
        list.setListData(drugstores.toArray());
    }

    private void onOK() {
        // add your code here
        dispose();
    }

}
