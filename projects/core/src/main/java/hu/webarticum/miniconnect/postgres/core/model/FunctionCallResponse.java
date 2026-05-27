package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Function-call result message. */
public final class FunctionCallResponse implements TaggedMessage, BackendMessage {

    private final NullableValue result;

    public FunctionCallResponse(NullableValue result) {
        this.result = Objects.requireNonNull(result, "result");
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
                .add("resultIsNull", result.isNull())
                .add("resultLength", result.isNull() ? 0 : result.bytes().length())
                .build();
    }

}
