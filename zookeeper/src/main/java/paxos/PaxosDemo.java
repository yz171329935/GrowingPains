package paxos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marco on 2017/5/19.
 */
public class PaxosDemo {


    public static void main(String[] args) {
        List<Acceptor> acceptors = new ArrayList<Acceptor>();
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        for (String name : list) {
            acceptors.add(new Acceptor(name));
        }
        Proposer.vote(new Proposal(1L, null), acceptors);
    }


}
