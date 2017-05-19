package paxos;

import java.util.*;

/**
 * Created by marco on 2017/5/19.
 */
public class Proposer {

    private static final Random RANDOM = new Random();
    private static final String[] PROPOSALS = {"ProjectA", "ProjectB", "ProjectC"};

    public static void vote(Proposal proposal, Collection<Acceptor> acceptors) {
        int quorum = acceptors.size()/2 + 1;
        int count = 0;
        while (true) {
            printInfo("VOTE_ROUND", "START", ++count + "");
            List<Proposal> proposals = new ArrayList<Proposal>();
            for (Acceptor acceptor : acceptors) {
                Promise promise = acceptor.onPrepare(proposal);
                if (promise != null && promise.isAck())
                    proposals.add(promise.getProposal());
            }
            if (proposals.size() < quorum) {
                printInfo("PROPOSER[" + proposal + "]", "VOTE", "NOT PREPARED");
                proposal = nextProposal(proposal.getVoteNumber(), proposals);
                continue;
            }
            int acceptCount = 0;
            for (Acceptor acceptor : acceptors) {
                if (acceptor.onAccept(proposal))
                    acceptCount++;
            }
            if (acceptCount < quorum) {
                printInfo("PROPOSER[" + proposal + "]", "VOTE", "NOT ACCEPTED");
                proposal = nextProposal(proposal.getVoteNumber(), proposals);
                continue;
            }
            break;
        }
        printInfo("PROPOSER[" + proposal + "]", "VOTE", "SUCCESS");
    }

    private static void printInfo(String subject, String operation, String result) {
        System.out.println(subject + ":" + operation + "<" + result + ">");
    }


    /**
     * 对于提案的约束，第三条约束要求：
     * 如果maxVote不存在，那么没有限制，下一次表决可以使用任意提案；
     * 否则，下一次表决要沿用maxVote的提案
     *
     * @param currentVoteNumber
     * @param proposals
     * @return
     */
    private static Proposal nextProposal(long currentVoteNumber, List<Proposal> proposals) {
        long voteNumber = currentVoteNumber + 1;
        if (proposals.isEmpty())
            return new Proposal(voteNumber, PROPOSALS[RANDOM.nextInt(PROPOSALS.length)]);
        Collections.sort(proposals);
        Proposal maxVote = proposals.get(proposals.size() - 1);
        long maxVoteNumber = maxVote.getVoteNumber();
        String content = maxVote.getContent();
        if (maxVoteNumber >= currentVoteNumber)
            throw new IllegalStateException("illegal state maxVoteNumber");
        if (content != null)
            return new Proposal(voteNumber, content);
        else return new Proposal(voteNumber, PROPOSALS[RANDOM.nextInt(PROPOSALS.length)]);
    }

}
