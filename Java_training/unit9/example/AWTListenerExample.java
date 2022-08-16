package demo.unit9.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTListenerExample {

    public static void main(String[] args) {
        JFrame screen = new JFrame();

        Button btnClick = new Button("Click here");

        screen.add(btnClick);
        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello em", "Java sample", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        screen.setSize(200, 300);
        screen.setVisible(true);
    }

}
