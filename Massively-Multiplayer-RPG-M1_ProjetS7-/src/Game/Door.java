package Game;

import java.util.HashMap;
import java.util.Map;

public class Door {

    private Hall hall1;
    private Hall hall2;

    public Hall get_Other_Hall(Hall hall){
        if (hall == hall1){
        return hall2;}
        else if (hall == hall2){
            return hall1;
        }
    }

    public void add_Hall(Hall hall){


    }

}
