package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** ExecuteMessage command for a portal. */
public final class ExecuteMessage implements TaggedMessage, FrontendMessage {

    private final CString portalName;

    private final int maximumRowCount;

    public ExecuteMessage(CString portalName, int maximumRowCount) {
        this.portalName = Objects.requireNonNull(portalName, "portalName");
        if (maximumRowCount < 0) {
            throw new IllegalArgumentException("maximumRowCount must be non-negative");
        }
        this.maximumRowCount = maximumRowCount;
    }

    /** Portal name to execute, empty for the unnamed portal. */
    public CString portalName() {
        return portalName;
    }

    /** Maximum rows to return, or zero for no limit. */
    public int maximumRowCount() {
        return maximumRowCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(portalName, maximumRowCount);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ExecuteMessage)) {
            return false;
        }
        ExecuteMessage otherExecute = (ExecuteMessage) other;
        return
                maximumRowCount == otherExecute.maximumRowCount &&
                portalName.equals(otherExecute.portalName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("portalName", portalName)
                .add("maximumRowCount", maximumRowCount)
                .build();
    }

}
