package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Simple QueryMessage protocol request containing a query string. */
public final class QueryMessage implements TaggedMessage, FrontendMessage {

    private final CString query;

    public QueryMessage(CString query) {
        this.query = Objects.requireNonNull(query, "query");
    }

    /** QueryMessage string to execute using the Simple QueryMessage protocol. */
    public CString query() {
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
        } else if (!(other instanceof QueryMessage)) {
            return false;
        }
        QueryMessage otherQuery = (QueryMessage) other;
        return query.equals(otherQuery.query);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("queryByteLength", query.bytes().length())
                .build();
    }

}
