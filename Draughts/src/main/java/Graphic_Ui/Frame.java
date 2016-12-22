package Graphic_Ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Core.Api;
import Core.Field;

public class Frame extends JFrame{

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Frame frame = new Frame();
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public Frame() {
        super("Draughts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton new_game_button = new JButton("Начать игру");
        new_game_button.setSize(200,50);
        new_game_button.setLocation(300,150);
        panel.add(new_game_button);
        JButton load_button = new JButton("Загрузить поле");
        load_button.setSize(200,50);
        load_button.setLocation(300,250);
        panel.add(load_button);
        JButton exit_button = new JButton("Выход");
        exit_button.setSize(200,50);
        exit_button.setLocation(300,350);
        panel.add(exit_button);
        ActionListener actionListenerNewGame = new NewGameListener();
        new_game_button.addActionListener(actionListenerNewGame);
        ActionListener actionListenerLoad = new LoadListener();
        load_button.addActionListener(actionListenerLoad);
        ActionListener actionListenerExit = new ExitListener();
        exit_button.addActionListener(actionListenerExit);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 100));
    }

    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Field field = Api.load_file();
                Board board = new Board(field);
                board.setSize(800, 600);
                board.setLocationRelativeTo(null);
                board.setVisible(true);
                dispose();
            } catch (FileNotFoundException e1) {
                System.out.println("Неудалось загрузить поле из файла.");
            }
        }
    }

    public class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Field field = new Field();
            Board board = new Board(field);
            board.setSize(800, 600);
            board.setLocationRelativeTo(null);
            board.setVisible(true);
            dispose();
        }
    }
}
