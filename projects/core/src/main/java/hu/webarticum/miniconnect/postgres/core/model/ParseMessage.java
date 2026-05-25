package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** ParseMessage command creating a prepared statement from a query string. */
public final class ParseMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'P';

    private final String preparedStatementName;

    private final String query;

    private final ImmutableList<Integer> parameterTypeObjectIds;

    public ParseMessage(String preparedStatementName, String query, ImmutableList<Integer> parameterTypeObjectIds) {
        this.preparedStatementName = Objects.requireNonNull(preparedStatementName, "preparedStatementName");
        this.query = Objects.requireNonNull(query, "query");
        this.parameterTypeObjectIds = Objects.requireNonNull(parameterTypeObjectIds, "parameterTypeObjectIds");
        parameterTypeObjectIds.forEach(v -> Objects.requireNonNull(v, "parameterTypeObjectId"));
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Prepared statement name, empty for the unnamed statement. */
    public String preparedStatementName() {
        return preparedStatementName;
    }

    /** QueryMessage string to parse. */
    public String query() {
        return query;
    }

    /** Object IDs of explicitly specified parameter types. */
    public ImmutableList<Integer> parameterTypeObjectIds() {
        return parameterTypeObjectIds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(preparedStatementName, query, parameterTypeObjectIds);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ParseMessage)) {
            return false;
        }
        ParseMessage otherParse = (ParseMessage) other;
        return
                preparedStatementName.equals(otherParse.preparedStatementName) &&
                query.equals(otherParse.query) &&
                parameterTypeObjectIds.equals(otherParse.parameterTypeObjectIds);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("preparedStatementName", preparedStatementName)
                .add("query", query)
                .add("parameterTypeObjectIds", parameterTypeObjectIds)
                .build();
    }

}
