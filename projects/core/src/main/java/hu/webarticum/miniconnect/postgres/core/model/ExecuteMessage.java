package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** ExecuteMessage command for a portal. */
public final class ExecuteMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'E';

    private final String portalName;

    private final int maximumRowCount;

    public ExecuteMessage(String portalName, int maximumRowCount) {
        this.portalName = Objects.requireNonNull(portalName, "portalName");
        this.maximumRowCount = maximumRowCount;
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Portal name to execute, empty for the unnamed portal. */
    public String portalName() {
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
        }
        if (!(other instanceof ExecuteMessage)) {
            return false;
        }
        ExecuteMessage otherExecute = (ExecuteMessage) other;
        return maximumRowCount == otherExecute.maximumRowCount
                && portalName.equals(otherExecute.portalName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("portalName", portalName)
                .add("maximumRowCount", maximumRowCount)
                .build();
    }

}
