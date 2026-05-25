package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Function-call request addressed by function object ID. */
public final class FunctionCallMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'F';

    private final int functionObjectId;

    private final ImmutableList<FormatCode> argumentFormatCodes;

    private final ImmutableList<NullableValue> arguments;

    private final FormatCode resultFormatCode;

    public FunctionCallMessage(
            int functionObjectId,
            ImmutableList<FormatCode> argumentFormatCodes,
            ImmutableList<NullableValue> arguments,
            FormatCode resultFormatCode) {
        this.functionObjectId = functionObjectId;
        this.argumentFormatCodes = Objects.requireNonNull(argumentFormatCodes, "argumentFormatCodes");
        argumentFormatCodes.forEach(v -> Objects.requireNonNull(v, "argumentFormatCode"));
        this.arguments = Objects.requireNonNull(arguments, "arguments");
        arguments.forEach(v -> Objects.requireNonNull(v, "argument"));
        this.resultFormatCode = Objects.requireNonNull(resultFormatCode, "resultFormatCode");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Object ID of the function to call. */
    public int functionObjectId() {
        return functionObjectId;
    }

    /** Format codes for function argument values. */
    public ImmutableList<FormatCode> argumentFormatCodes() {
        return argumentFormatCodes;
    }

    /** Argument values supplied to the function. */
    public ImmutableList<NullableValue> arguments() {
        return arguments;
    }

    /** Format code requested for the function result. */
    public FormatCode resultFormatCode() {
        return resultFormatCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionObjectId, argumentFormatCodes, arguments, resultFormatCode);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof FunctionCallMessage)) {
            return false;
        }
        FunctionCallMessage otherFunctionCall = (FunctionCallMessage) other;
        return
                functionObjectId == otherFunctionCall.functionObjectId &&
                resultFormatCode == otherFunctionCall.resultFormatCode &&
                argumentFormatCodes.equals(otherFunctionCall.argumentFormatCodes) &&
                arguments.equals(otherFunctionCall.arguments);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("functionObjectId", functionObjectId)
                .add("argumentFormatCodes", argumentFormatCodes)
                .add("arguments", arguments)
                .add("resultFormatCode", resultFormatCode)
                .build();
    }

}
