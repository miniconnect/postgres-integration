package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Function-call request addressed by function object ID.
 */
public final class FunctionCallMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'F';

    private final int functionObjectId;

    private final ImmutableList<Integer> argumentFormatCodes;

    private final ImmutableList<NullableValue> arguments;

    private final int resultFormatCode;

    public FunctionCallMessage(
            int functionObjectId,
            ImmutableList<Integer> argumentFormatCodes,
            ImmutableList<NullableValue> arguments,
            int resultFormatCode) {
        this.functionObjectId = functionObjectId;
        this.argumentFormatCodes = Objects.requireNonNull(argumentFormatCodes, "argumentFormatCodes");
        for (Integer argumentFormatCode : argumentFormatCodes) {
            Objects.requireNonNull(argumentFormatCode, "argumentFormatCode");
        }
        this.arguments = Objects.requireNonNull(arguments, "arguments");
        for (NullableValue argument : arguments) {
            Objects.requireNonNull(argument, "argument");
        }
        this.resultFormatCode = resultFormatCode;
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Object ID of the function to call.
     */
    public int getFunctionObjectId() {
        return functionObjectId;
    }

    /**
     * Format codes for function argument values.
     */
    public ImmutableList<Integer> getArgumentFormatCodes() {
        return argumentFormatCodes;
    }

    /**
     * Argument values supplied to the function.
     */
    public ImmutableList<NullableValue> getArguments() {
        return arguments;
    }

    /**
     * Format code requested for the function result.
     */
    public int getResultFormatCode() {
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
        }
        if (!(other instanceof FunctionCallMessage)) {
            return false;
        }
        FunctionCallMessage otherFunctionCall = (FunctionCallMessage) other;
        return functionObjectId == otherFunctionCall.functionObjectId
                && resultFormatCode == otherFunctionCall.resultFormatCode
                && argumentFormatCodes.equals(otherFunctionCall.argumentFormatCodes)
                && arguments.equals(otherFunctionCall.arguments);
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
