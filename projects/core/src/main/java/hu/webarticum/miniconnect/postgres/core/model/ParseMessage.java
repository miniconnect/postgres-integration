package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** ParseMessage command creating a prepared statement from a query string. */
public final class ParseMessage implements TaggedMessage, FrontendMessage {

    private final CString preparedStatementName;

    private final CString query;

    private final ImmutableList<Integer> parameterTypeObjectIds;

    public ParseMessage(CString preparedStatementName, CString query, ImmutableList<Integer> parameterTypeObjectIds) {
        this.preparedStatementName = Objects.requireNonNull(preparedStatementName, "preparedStatementName");
        this.query = Objects.requireNonNull(query, "query");
        this.parameterTypeObjectIds = Objects.requireNonNull(parameterTypeObjectIds, "parameterTypeObjectIds");
        int parameterTypeObjectIdCount = parameterTypeObjectIds.size();
        if (parameterTypeObjectIdCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "parameterTypeObjectIds size must be at most 65535, but was %d",
                    parameterTypeObjectIdCount));
        }
        parameterTypeObjectIds.forEach(v -> Objects.requireNonNull(v, "parameterTypeObjectId"));
    }

    /** Prepared statement name, empty for the unnamed statement. */
    public CString preparedStatementName() {
        return preparedStatementName;
    }

    /** QueryMessage string to parse. */
    public CString query() {
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
                .add("queryByteLength", query.bytes().length())
                .add("parameterTypeObjectIdCount", parameterTypeObjectIds.size())
                .build();
    }

}
