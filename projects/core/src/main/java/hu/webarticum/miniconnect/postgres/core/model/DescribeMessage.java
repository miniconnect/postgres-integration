package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** DescribeMessage command for a prepared statement or portal. */
public final class DescribeMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'D';

    private final NamedObjectType targetType;

    private final String targetName;

    public DescribeMessage(NamedObjectType targetType, String targetName) {
        this.targetType = Objects.requireNonNull(targetType, "targetType");
        this.targetName = Objects.requireNonNull(targetName, "targetName");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Whether the described target is a prepared statement or portal. */
    public NamedObjectType targetType() {
        return targetType;
    }

    /** Name of the prepared statement or portal to describe. */
    public String targetName() {
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
        if (!(other instanceof DescribeMessage)) {
            return false;
        }
        DescribeMessage otherDescribe = (DescribeMessage) other;
        return targetType == otherDescribe.targetType
                && targetName.equals(otherDescribe.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetType", targetType)
                .add("targetName", targetName)
                .build();
    }

}
