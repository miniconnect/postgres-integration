package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Parameter type description for a prepared statement. */
public final class ParameterDescriptionMessage implements TaggedMessage, BackendMessage {

    private final ImmutableList<Integer> parameterTypeObjectIds;

    public ParameterDescriptionMessage(ImmutableList<Integer> parameterTypeObjectIds) {
        this.parameterTypeObjectIds = Objects.requireNonNull(parameterTypeObjectIds, "parameterTypeObjectIds");
        int parameterTypeObjectIdCount = parameterTypeObjectIds.size();
        if (parameterTypeObjectIdCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "parameterTypeObjectIds size must be at most 65535, but was %d",
                    parameterTypeObjectIdCount));
        }
        parameterTypeObjectIds.forEach(v -> Objects.requireNonNull(v, "parameterTypeObjectId"));
    }

    /** Object IDs of statement parameter data types. */
    public ImmutableList<Integer> parameterTypeObjectIds() {
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
        } else if (!(other instanceof ParameterDescriptionMessage)) {
            return false;
        }
        ParameterDescriptionMessage otherParameterDescription = (ParameterDescriptionMessage) other;
        return parameterTypeObjectIds.equals(otherParameterDescription.parameterTypeObjectIds);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("parameterTypeObjectIdCount", parameterTypeObjectIds.size())
                .build();
    }

}
