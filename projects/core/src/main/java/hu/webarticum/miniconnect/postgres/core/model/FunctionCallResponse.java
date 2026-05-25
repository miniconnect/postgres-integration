package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Function-call result message. */
public final class FunctionCallResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'V';

    private final NullableValue result;

    public FunctionCallResponse(NullableValue result) {
        this.result = Objects.requireNonNull(result, "result");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Function result value. */
    public NullableValue result() {
        return result;
    }

    @Override
    public int hashCode() {
        return result.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof FunctionCallResponse)) {
            return false;
        }
        FunctionCallResponse otherFunctionCallResponse = (FunctionCallResponse) other;
        return result.equals(otherFunctionCallResponse.result);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("result", result)
                .build();
    }

}
