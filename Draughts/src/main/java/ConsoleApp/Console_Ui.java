package ConsoleApp;

import Core.Api;
import Core.Draught;
import Core.Field;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Console_Ui {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        print_menu(s);
    }

    private static void print_menu(Scanner s) {
        int choice = -1;
        Field field = new Field();
        while(choice != 0) {
            System.out.println("1.Создать новое поле.");
            System.out.println("2.Загрузить поле.");
            System.out.println("0.Выход.");
            choice = s.nextInt();
            switch (choice)
            {
                case 1:
                    print_field(field);
                    secondary_menu(field, s);
                    break;
                case 2:
                    try {
                        field = Api.load_file();
                    } catch (FileNotFoundException e) {
                        System.out.println("Неудалось загрузить поле из файла.");
                    }
                    print_field(field);
                    secondary_menu(field, s);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Некорректная команда.");
                    break;
            }
        }
    }

    private static void secondary_menu(Field field, Scanner s) {
        int choice = -1;
        while(choice != 0) {
            System.out.println("1.Сделать ход.");
            System.out.println("2.Показать поле.");
            System.out.println("3.Уничтожить шашку соперника.");
            System.out.println("4.Сохранить поле.");
            System.out.println("0.Назад.");
            choice = s.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Введите координаты шашки.");
                    int x_draught, y_draught;
                    x_draught = s.nextInt();
                    y_draught = s.nextInt();
                    System.out.println("Введите координаты места.");
                    int x_place, y_place;
                    x_place = s.nextInt();
                    y_place = s.nextInt();
                    try {
                        Api.move_draught(field, x_draught, y_draught, x_place, y_place);
                    } catch (IllegalArgumentException e){
                        System.out.println("Неверные координаты шага.");
                    }
                    secondary_menu(field, s);
                    break;
                case 2:
                    print_field(field);
                    secondary_menu(field, s);
                    break;
                case 3:
                    System.out.println("Введите координаты шашки.");
                    int x_selected, y_selected;
                    x_selected = s.nextInt();
                    y_selected = s.nextInt();
                    System.out.println("Введите координаты уничтожаемой шашки.");
                    int x_destroyed, y_destroyed;
                    x_destroyed = s.nextInt();
                    y_destroyed = s.nextInt();
                    try {
                        Api.destroy_draught(field, x_selected, y_selected, x_destroyed, y_destroyed);
                    } catch (IllegalArgumentException e){
                        System.out.println("Неверные координаты уничтожаемой шашки.");
                    }
                    secondary_menu(field, s);
                    break;
                case 4:
                    try {
                        Api.save_file(field);
                    } catch (IOException e) {
                        System.out.println("Неудалось сохранить поле в файл.");
                    }
                    secondary_menu(field,s);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Некорректная команда.");
                    break;
            }
            break;
        }
    }

    private static void print_field(Field field) {
        for (int i = 0; i <= 7; i++){
            System.out.print("|");
            for (int j = 0; j <= 7; j++){
                Draught draught = field.get_draught(i,j);
                if (draught == null) {
                    System.out.print(" ");
                }else {
                    if ((draught.get_color() == false) && (draught.get_type() == false)) {
                        System.out.print("b");
                    }
                    if ((draught.get_color() == false) && (draught.get_type() == true)) {
                        System.out.print("B");
                    }
                    if ((draught.get_color() == true) && (draught.get_type() == false)) {
                        System.out.print("w");
                    }
                    if ((draught.get_color() == true) && (draught.get_type() == true)) {
                        System.out.print("W");
                    }
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
