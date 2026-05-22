package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Parameter type description for a prepared statement.
 */
public final class ParameterDescriptionMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 't';

    private final ImmutableList<Integer> parameterTypeObjectIds;

    public ParameterDescriptionMessage(ImmutableList<Integer> parameterTypeObjectIds) {
        this.parameterTypeObjectIds = Objects.requireNonNull(parameterTypeObjectIds, "parameterTypeObjectIds");
        for (Integer parameterTypeObjectId : parameterTypeObjectIds) {
            Objects.requireNonNull(parameterTypeObjectId, "parameterTypeObjectId");
        }
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Object IDs of statement parameter data types.
     */
    public ImmutableList<Integer> getParameterTypeObjectIds() {
        return parameterTypeObjectIds;
    }

    @Override
    public int hashCode() {
        return parameterTypeObjectIds.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParameterDescriptionMessage)) {
            return false;
        }
        ParameterDescriptionMessage otherParameterDescription =
                (ParameterDescriptionMessage) other;
        return parameterTypeObjectIds.equals(otherParameterDescription.parameterTypeObjectIds);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("parameterTypeObjectIds", parameterTypeObjectIds)
                .build();
    }

}
