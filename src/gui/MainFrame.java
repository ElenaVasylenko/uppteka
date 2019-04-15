package gui;

import com.company.DemoLogic;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DemoLogic demoLogic;

    public MainFrame() {

        demoLogic = new DemoLogic();

        setTitle("Головне меню");
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new MainMenu(this).getView());
        pack();

    }

    public void changeView(View currentView, View nextView){
        remove(currentView.getView());
        invalidate();
        add(nextView.getView());
        validate();
        setTitle(nextView.getTitle());
        repaint();
    }

    public DemoLogic getDemoLogic() {
        return demoLogic;
    }
}
