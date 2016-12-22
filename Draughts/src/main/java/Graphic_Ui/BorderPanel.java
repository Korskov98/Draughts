package Graphic_Ui;

import Core.Api;
import Core.Draught;
import Core.Field;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BorderPanel extends JPanel{
    private Field field;
    private Board board;
    private boolean click = false;
    private int x_first_click = -10;
    private int y_first_click = -10;
    private int x_second_click;
    private int y_second_click;

    public BorderPanel(Field field, Board board) {
        this.field = field;
        this.board = board;
        setOpaque(true);
        this.addMouseListener(new CustomListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int i = 0,x = 150;
        g2d.setColor(Color.GRAY);
        while (i <= 7){
            g2d.fillRect(i*60 + x,0,60,60);
            i = i + 2;
        }
        i = 1;
        while (i <= 7){
            g2d.fillRect(i*60 + x,60,60,60);
            i = i + 2;
        }
        i = 0;
        while (i <= 7){
            g2d.fillRect(i*60 + x,120,60,60);
            i = i + 2;
        }
        i = 1;
        while (i <= 7){
            g2d.fillRect(i*60 + x,180,60,60);
            i = i + 2;
        }
        i = 0;
        while (i <= 7){
            g2d.fillRect(i*60 + x,240,60,60);
            i = i + 2;
        }
        i = 1;
        while (i <= 7) {
            g2d.fillRect(i*60 + x,300,60,60);
            i = i + 2;
        }
        i = 0;
        while (i <= 7){
            g2d.fillRect(i*60 + x,360,60,60);
            i = i + 2;
        }
        i = 1;
        while (i <= 7) {
            g2d.fillRect(i*60 + x,420,60,60);
            i = i + 2;
        }
        for(i = 0; i <= 7; ++i){
            for (int j = 0; j <= 7; ++j){
                Draught draught = field.get_draught(i,j);
                if (draught != null) {
                    if (draught.get_color() == true) {
                        g2d.setColor(Color.WHITE);
                        g2d.fillOval(j * 60 + x + 5, i * 60 + 5, 50, 50);
                    }
                    if (draught.get_color() == false) {
                        g2d.setColor(Color.BLACK);
                        g2d.fillOval(j * 60 + x + 5, i * 60 + 5, 50, 50);
                    }
                    if (draught.get_type() == true) {
                        g2d.setColor(Color.YELLOW);
                        int[] xPoints = {j * 60 + x + 10, j * 60 + x + 10, j * 60 + x + 20, j * 60 + x + 30, j * 60 + x + 40, j * 60 + x + 50, j * 60 + x + 50};
                        int[] yPoints = {i * 60 + 45, i * 60 + 15, i * 60 + 30, i * 60 + 15, i * 60 + 30, i * 60 + 15, i * 60 + 45};
                        g2d.fillPolygon(xPoints,yPoints,7);
                    }
                }
            }
        }
        g2d.setColor(Color.RED);
        g2d.fillOval(x_first_click * 60 + x + 5,y_first_click * 60 + 5,50,50);
        boolean color = field.get_color();
        g2d.setColor(Color.BLACK);
        if (color == true){
            g2d.drawString("Ход белых шашек.",150,490);
        }else {
            g2d.drawString("Ход чёрных шашек.",150,490);
        }
        int[] statistics = Api.get_statistics(field);
        g2d.drawString("Ост. белых шашек: " + statistics[0],15,15);
        g2d.drawString("Белых дамок: " + statistics[1],15,30);
        g2d.drawString("Белых шашек уничт.: " + statistics[2],15,45);
        g2d.drawString("Ост. чёрных шашек: " + statistics[3],635,15);
        g2d.drawString("Чёрных дамок: " + statistics[4],635,30);
        g2d.drawString("Чёрных шашек уничт.: " + statistics[5],635,45);
    }

    public class CustomListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            click = !click;
            if (click == true){
                x_first_click = (e.getX() - 150) / 60;
                y_first_click = e.getY() / 60;
                if (field.get_draught(y_first_click,x_first_click).get_color() != field.get_color()){
                    x_first_click = -10;
                    y_first_click = -10;
                    click = !click;
                    JOptionPane.showMessageDialog(board, "Вы выбрали шашку не того цвета.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }else {
                    repaint();
                }
            }else {
                x_second_click = (e.getX() - 150) / 60;
                y_second_click = e.getY() / 60;
                try {
                    Api.move_draught(field, y_first_click,x_first_click,y_second_click,x_second_click);
                    repaint();
                }catch (IllegalArgumentException ex){
                    try {
                        Api.destroy_draught(field,y_first_click,x_first_click,y_second_click,x_second_click);
                        repaint();
                    }catch (IllegalArgumentException exc){
                        JOptionPane.showMessageDialog(board, "Данный ход невозможен.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
                x_first_click = -10;
                y_first_click = -10;
            }
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }
    }
}
