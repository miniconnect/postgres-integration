package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Describe command for a statement. */
public final class DescribeStatementMessage implements TaggedMessage, FrontendMessage {

    private final CString statementName;

    public DescribeStatementMessage(CString statementName) {
        this.statementName = Objects.requireNonNull(statementName, "statementName");
    }

    /** Name of the statement to describe. */
    public CString statementName() {
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
        } else if (!(other instanceof DescribeStatementMessage)) {
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
