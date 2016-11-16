package Core;

import java.lang.Math;

public class Draught {
    private int x;
    private int y;
    private boolean color;
    private boolean type;

    public Draught(int x, int y, boolean color, boolean type){
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
    }

    public boolean check_of_move(int x, int y, boolean type){
        if (type == false) {
            if (this.color == true) {
                if ((x - this.x == 1) && (Math.abs(y - this.y) == 1) && (x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((this.x - x == 1) && (Math.abs(y - this.y) == 1) && (x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)) {
                    return true;
                } else {
                    return false;
                }
            }
        }else{
            if ((Math.abs(this.x - x) == Math.abs(this.y - y)) && (x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean check_of_type(){
        if (x == 7) {
            return true;
        }else{
            return false;
        }
    }

    public boolean get_type() {return type;}

    public void set_type(boolean type) {this.type = type;}

    public boolean get_color() { return color; }

    public void  set_x(int x) {this.x = x;}

    public void  set_y(int y) {this.y = y;}
}
