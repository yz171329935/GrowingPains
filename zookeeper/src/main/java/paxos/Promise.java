package paxos;

/**
 * Created by marco on 2017/5/19.
 */
public class Promise {

    private final boolean ack;
    private final Proposal proposal;

    public Promise(boolean ack, Proposal proposal) {
        this.ack = ack;
        this.proposal = proposal;
    }

    public boolean isAck() {
        return ack;
    }

    public Proposal getProposal() {
        return proposal;
    }
}
