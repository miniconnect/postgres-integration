package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Close command for a statement. */
public final class CloseStatementMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'C';

    private final String statementName;

    public CloseStatementMessage(String statementName) {
        this.statementName = Objects.requireNonNull(statementName, "statementName");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Name of the statement to close. */
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
        if (!(other instanceof CloseStatementMessage)) {
            return false;
        }
        CloseStatementMessage otherCloseStatement = (CloseStatementMessage) other;
        return statementName.equals(otherCloseStatement.statementName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("statementName", statementName)
                .build();
    }

}
