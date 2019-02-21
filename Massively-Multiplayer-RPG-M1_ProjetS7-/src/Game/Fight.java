package Game;

public class Fight {

    private Participant forward;
    private Participant attacked;

    public Fight(Participant forward, Participant attacked) {
        this.forward = forward;
        this.attacked = attacked;
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

    public void hitpoints(Participant participant, int hitpoint) {
        int lifeParticipant = participant.getLife();
        int life = lifeParticipant - hitpoint;
        participant.setLife(life);
    }
    public void alertFight(){}
    public void calculateHitpoint(){}
    public void chooseParticipant(){}
    public void deadParticipant(Participant participant){}
    public void endFight(){}
}
