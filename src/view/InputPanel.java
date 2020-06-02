package view;

import javax.swing.*;

import java.awt.*;
public class InputPanel {

    public JPanel panel;
    public JTextField nValue;
    public JTextField kValue;
    private JLabel labelK;
    private JLabel labelN;

    public InputPanel() {

        panel = new JPanel();
        nValue = new JTextField(3);
        kValue = new JTextField(3);
        panel.setLayout( new BoxLayout(panel,BoxLayout.Y_AXIS));
        labelK = new JLabel("Введите количество массивов");
        labelN = new JLabel("Введите количество элементов в массиве");
    }



    JPanel buildComponent() {
        //panel.setPreferredSize(new Dimension(100, 100));
        panel.setOpaque(true);
        panel.add(labelN);
        panel.add(nValue);
        panel.add(labelK);
        panel.add(kValue);
        panel.setVisible(true);
        return panel;

    }

    int getN() {

        String stringValue = nValue.getText();
        if (!stringValue.equals("")) {
            return Integer.parseInt(stringValue);
        }
        else
        {
            return  100;
        }
    }

    int getK() {
        String stringValue = kValue.getText();
        if (stringValue.equals("")) {

            return 1000;

        }
        else
        {
            return Integer.parseInt(stringValue);
        }
    }
}
