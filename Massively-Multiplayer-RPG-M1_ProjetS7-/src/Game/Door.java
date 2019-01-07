package Game;

import java.util.HashMap;
import java.util.Map;

public class Door {

    private int door;
    private static Map<Door,Pole> Porte_liste = new HashMap<Door,Pole>();
    private Hall hall = new Hall();
    public Door{

        this.door=door;
    }

    public Hall get_Other_Hall(Hall hall){

        return hall;
    }

    public void add_Hall(Hall hall){


    }

}
