package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Close command for a statement. */
public final class CloseStatementMessage implements TaggedMessage, FrontendMessage {

    private final CString statementName;

    public CloseStatementMessage(CString statementName) {
        this.statementName = Objects.requireNonNull(statementName, "statementName");
    }

    /** Name of the statement to close. */
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
        } else if (!(other instanceof CloseStatementMessage)) {
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
