package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Simple QueryMessage protocol request containing a query string.
 */
public final class QueryMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'Q';

    private final String query;

    public QueryMessage(String query) {
        this.query = Objects.requireNonNull(query, "query");
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * QueryMessage string to execute using the Simple QueryMessage protocol.
     */
    public String getQuery() {
        return query;
    }

    @Override
    public int hashCode() {
        return query.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueryMessage)) {
            return false;
        }
        QueryMessage otherQuery = (QueryMessage) other;
        return query.equals(otherQuery.query);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("query", query)
                .build();
    }

}
