import Core.Draught;
import Core.Field;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Draughts {
    @Test
    public void test_free_true(){
        Field field = new Field();
        assertTrue(field.check_free(5,0));
    }
    @Test
    public void test_free_false(){
        Field field = new Field();
        assertFalse(field.check_free(5,1));
    }
    @Test
    public void test_check_of_move_true(){
        Field field = new Field();
        Draught draught = field.get_draught(5,1);
        assertTrue(draught.check_of_move(4,2));
    }
    @Test
    public void test_check_of_move_false(){
        Field field = new Field();
        Draught draught = field.get_draught(5,1);
        assertFalse(draught.check_of_move(4,3));
    }
    @Test
    public void test_check_destruction_true(){
        Field field = new Field();
        Draught draught = new Draught(4,2,true,false);
        field.set_draught(draught);
        assertTrue(field.check_destruction(5,1,4,2));
    }
    @Test
    public void test_check_destruction_false(){
        Field field = new Field();
        Draught draught = new Draught(4,2,true,true);
        field.set_draught(draught);
        assertFalse(field.check_destruction(4,2,5,1));
    }
}
