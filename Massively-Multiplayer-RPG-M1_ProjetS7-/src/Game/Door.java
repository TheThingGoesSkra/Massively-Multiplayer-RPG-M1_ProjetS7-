package Game;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class Door {

    private String idDoor;
    private Hall hall1;
    private Hall hall2;

    public Door(String idDoor){
        this.idDoor=idDoor;
    };

    public Hall get_Other_Hall(Hall hall){
        if (hall == hall1){
        return hall2;}
        else if (hall == hall2){
            return hall1;
        }
        return null;
    }

    public void add_Hall(Hall hall){
        if (hall1 == null){
            hall1=hall;}
        else if (hall2 == null){
            hall2=hall;
        }else{
            // TODO erreur
            exit(1);
        }
    }

    public void setHall1(Hall hall1) {
        this.hall1 = hall1;
    }

    public void setHall2(Hall hall2) {
        this.hall2 = hall2;
    }

    public String getIdDoor() {
        return idDoor;
    }

    public void setIdDoor(String idDoor) {
        this.idDoor = idDoor;
    }
}
