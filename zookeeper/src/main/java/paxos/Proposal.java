package paxos;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by marco on 2017/5/19.
 */
public class Proposal implements Comparable<Proposal> {

    private static final HashFunction HASH_FUNCTION = Hashing.murmur3_32();

    private final long voteNumber;

    private final String content;


    public Proposal(long voteNumber, String content) {
        this.voteNumber = voteNumber;
        this.content = content;
    }

    public Proposal() {
        this(0, null);
    }

    public long getVoteNumber() {
        return voteNumber;
    }

    public String getContent() {
        return content;
    }

    public int compareTo(Proposal o) {
        long x = voteNumber;
        long y = o.getVoteNumber();
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Proposal))
            return false;
        Proposal proposal = (Proposal) obj;
        return voteNumber == proposal.voteNumber && StringUtils.equals(content, proposal.content);
    }

    @Override
    public int hashCode() {
        return HASH_FUNCTION
                .newHasher()
                .putLong(voteNumber)
                .putString(content, Charsets.UTF_8)
                .hash()
                .asInt();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(voteNumber)
                .append(':')
                .append(content)
                .toString();
    }
}
