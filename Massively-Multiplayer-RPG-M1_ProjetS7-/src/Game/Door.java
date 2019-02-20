package Game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class Door implements Serializable{

    private String idDoor;
    private Hall hall1;
    private Hall hall2;

    public Door(String idDoor){
        this.idDoor=idDoor;
        this.hall1=null;
        this.hall2=null;
    };

    public Hall getHall1() {
        return hall1;
    }

    public Hall getHall2() {
        return hall2;
    }

    public Hall getOtherHall(Hall hall){
        if (hall == hall1){
        return hall2;}
        else if (hall == hall2){
            return hall1;
        }
        return null;
    }

    public void addHall(Hall hall){
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
