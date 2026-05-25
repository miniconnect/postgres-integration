package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Describe command for a statement. */
public final class DescribeStatementMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'D';

    private final String statementName;

    public DescribeStatementMessage(String statementName) {
        this.statementName = Objects.requireNonNull(statementName, "statementName");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Name of the statement to describe. */
    public String statementName() {
        return statementName;
    }

    @Override
    public int hashCode() {
        return statementName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DescribeStatementMessage)) {
            return false;
        }
        DescribeStatementMessage otherDescribeStatement = (DescribeStatementMessage) other;
        return statementName.equals(otherDescribeStatement.statementName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("statementName", statementName)
                .build();
    }

}
