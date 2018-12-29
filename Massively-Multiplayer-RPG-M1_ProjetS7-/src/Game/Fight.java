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
}
