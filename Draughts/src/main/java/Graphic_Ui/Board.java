package Graphic_Ui;

import javax.swing.*;
import Core.Api;
import Core.Field;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Board extends JFrame{
    private Field field;

    public Board(Field field){
        super("Draughts");
        this.field = field;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderPanel panel = new BorderPanel(field,this);
        panel.setLayout(null);
        JButton save_button = new JButton("Сохранить поле");
        save_button.setSize(200,50);
        save_button.setLocation(50,500);
        panel.add(save_button);
        JButton back_button = new JButton("Назад");
        back_button.setSize(200,50);
        back_button.setLocation(550,500);
        panel.add(back_button);
        ActionListener actionListenerBack = new BackListener();
        back_button.addActionListener(actionListenerBack);
        ActionListener actionListenerSave = new SaveListener();
        save_button.addActionListener(actionListenerSave);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 100));
    }

    public class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Frame frame = new Frame();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            dispose();
        }
    }

    public class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Api.save_file(field);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
