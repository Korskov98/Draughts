package Core;

public class Field {
    private int x;
    private int y;
    private Draught[][] draughts;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        draughts = new Draught[x][y];
        int i = 0;
        while (i <= 7){
            draughts[0][i] = new Draught(0,i,true,false);
            i = i + 2;
        }
        //i = 1;
        //while (i <= 7){
        //    draughts[1][i] = new Draught(1,i,true);
        //    i = i + 2;
        //}
        //i = 0;
        //while (i <= 7){
        //    draughts[2][i] = new Draught(2,i,true);
        //    i = i + 2;
        //}
        //i = 1;
        //while (i <= 7){
        //    draughts[5][i] = new Draught(5,i,false);
        //    i = i + 2;
        //}
        //i = 0;
        //while (i <= 7){
        //    draughts[6][i] = new Draught(6,i,false);
        //    i = i + 2;
        //}
        //i = 1;
        //while (i <= 7) {
        //    draughts[7][i] = new Draught(7,i,false);
        //    i = i + 2;
        //}
    }

    public boolean check_free(int x, int y){
        if (draughts[x][y] == null){
            return true;
        }else {
            return false;
        }
    }

    public boolean check_destruction(int x_selected, int y_selected, int x_destroyed, int y_destroyed){
        if ((draughts[x_destroyed][y_destroyed] != null) && (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color()) && (check_free(x_selected + 2 * (x_destroyed - x_selected), y_selected + 2 * (y_destroyed - y_selected))) && (x_selected + 2 * (x_destroyed - x_selected) >= 0) && (x_selected + 2 * (x_destroyed - x_selected) <= 7) && (y_selected + 2 * (y_destroyed - y_selected) >= 0) && (y_selected + 2 * (y_destroyed - y_selected) <= 7)){
            return true;
        }else {
            return false;
        }
    }

    public  Draught get_draught(int x, int y){return draughts[x][y];}

    public void set_draught(Draught draught, int x, int y){
        draughts[x][y] = draught;
    }

    public void  set_null(int x, int y){
        draughts[x][y] = null;
    }
}
