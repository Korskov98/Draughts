package Core;

public class Api {
    Field field;

    public void initialize_field(int x, int y){
        field = new Field(x, y);
    }

    public void print_field() {
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

    public void move_draught(int x_draught, int y_draught, int x_place, int y_place){
        Draught draught = field.get_draught(x_draught, y_draught);
        if (draught.check_of_move(x_place, y_place, draught.get_type()) == false){
            throw new IllegalArgumentException();
        }else {
            if (field.check_free(x_place, y_place) == false) {
                throw new IllegalArgumentException();
            } else {
                field.set_null(x_draught, y_draught);
                draught.set_x(x_place);
                draught.set_y(y_place);
                field.set_draught(draught, x_place, y_place);
                if (draught.check_of_type() == true){
                    draught.set_type(true);
                }
            }
        }
    }

    public void destroy_draught(int x_selected, int y_selected, int x_destroyed, int y_destroyed){
        Draught draught = field.get_draught(x_selected, y_selected);
        if (field.check_destruction(x_selected, y_selected, x_destroyed, y_destroyed) == false){
            throw new IllegalArgumentException();
        }else {
           field.set_null(x_selected, y_selected);
           field.set_null(x_destroyed, y_destroyed);
           draught.set_x(x_selected + 2 * (x_destroyed - x_selected));
           draught.set_y(y_selected + 2 * (y_destroyed - y_selected));
           field.set_draught(draught, x_selected + 2 * (x_destroyed - x_selected), y_selected + 2 * (y_destroyed - y_selected));
           draught.check_of_type();
           if (draught.check_of_type() == true){
               draught.set_type(true);
           }
        }
    }
}
