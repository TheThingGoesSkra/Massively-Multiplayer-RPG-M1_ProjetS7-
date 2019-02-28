package Game;

import java.util.List;

public class Fight extends Thread {

    private Participant forward;
    private Participant attacked;
    private String idLabyrinth;
    private String idHall;
    private Context context;
    private List<Fight> fights;

    public Fight(Participant forward, Participant attacked, Context context, List<Fight> fights, String idHall, String idLabyrinth) {
        this.forward = forward;
        this.attacked = attacked;
        this.idLabyrinth=idLabyrinth;
        this.idHall=idHall;
        this.context=context;
        this.fights=fights;
    }

    public Participant getForward() {
        return forward;
    }

    public void setForward(Participant forward) {
        this.forward = forward;
    }

    public Participant getAttacked() {
        return attacked;
    }

    public void setAttacked(Participant attacked) {
        this.attacked = attacked;
    }

    public void alertFight(){}
    public void calculateHitpoint(){}
    public void chooseParticipant(){}
    public void deadParticipant(Participant participant){}
    public void endFight(){}

    public void run(){
        while(true){

        }
    }
}
