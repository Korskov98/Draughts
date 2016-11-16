package ConsoleApp;

import Core.Api;
import java.util.Scanner;

public class Console_Ui {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        print_menu(s);
    }

    private static void print_menu(Scanner s) {
        int choice = -1;
        Api api = new Api();
        while(choice != 0) {
            System.out.println("1.Создать новое поле.");
            System.out.println("0.Выход.");
            choice = s.nextInt();
            int x = 8,y = 8;
            switch (choice)
            {
                case 1:
                    api.initialize_field(x,y);
                    api.print_field();
                    secondary_menu(api, s);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Некорректная команда.");
                    break;
            }
        }
    }

    private static void secondary_menu(Api api, Scanner s) {
        int choice = -1;
        while(choice != 0) {
            System.out.println("1.Сделать ход.");
            System.out.println("2.Показать поле.");
            System.out.println("3.Уничтожить шашку соперника.");
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
                        api.move_draught(x_draught, y_draught, x_place, y_place);
                    } catch (IllegalArgumentException e){
                        System.out.println("Неверные координаты шага.");
                    }
                    secondary_menu(api, s);
                    break;
                case 2:
                    api.print_field();
                    secondary_menu(api, s);
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
                        api.destroy_draught(x_selected, y_selected, x_destroyed, y_destroyed);
                    } catch (IllegalArgumentException e){
                        System.out.println("Неверные координаты уничтожаемой шашки.");
                    }
                    secondary_menu(api, s);
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

}
