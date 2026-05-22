package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * CloseMessage command for a prepared statement or portal.
 */
public final class CloseMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'C';

    private final NamedObjectType targetType;

    private final String targetName;

    public CloseMessage(NamedObjectType targetType, String targetName) {
        this.targetType = Objects.requireNonNull(targetType, "targetType");
        this.targetName = Objects.requireNonNull(targetName, "targetName");
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Whether the close target is a prepared statement or portal.
     */
    public NamedObjectType getTargetType() {
        return targetType;
    }

    /**
     * Name of the prepared statement or portal to close.
     */
    public String getTargetName() {
        return targetName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetType, targetName);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CloseMessage)) {
            return false;
        }
        CloseMessage otherClose = (CloseMessage) other;
        return targetType == otherClose.targetType
                && targetName.equals(otherClose.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetType", targetType)
                .add("targetName", targetName)
                .build();
    }

}
