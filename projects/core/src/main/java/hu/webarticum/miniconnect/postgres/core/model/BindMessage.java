package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * BindMessage command that creates a portal from a prepared statement.
 */
public final class BindMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'B';

    private final String destinationPortalName;

    private final String sourcePreparedStatementName;

    private final ImmutableList<Integer> parameterFormatCodes;

    private final ImmutableList<NullableValue> parameterValues;

    private final ImmutableList<Integer> resultFormatCodes;

    public BindMessage(
            String destinationPortalName,
            String sourcePreparedStatementName,
            ImmutableList<Integer> parameterFormatCodes,
            ImmutableList<NullableValue> parameterValues,
            ImmutableList<Integer> resultFormatCodes) {
        this.destinationPortalName = Objects.requireNonNull(destinationPortalName, "destinationPortalName");
        this.sourcePreparedStatementName = Objects.requireNonNull(sourcePreparedStatementName, "sourcePreparedStatementName");
        this.parameterFormatCodes = Objects.requireNonNull(parameterFormatCodes, "parameterFormatCodes");
        for (Integer parameterFormatCode : parameterFormatCodes) {
            Objects.requireNonNull(parameterFormatCode, "parameterFormatCode");
        }
        this.parameterValues = Objects.requireNonNull(parameterValues, "parameterValues");
        for (NullableValue parameterValue : parameterValues) {
            Objects.requireNonNull(parameterValue, "parameterValue");
        }
        this.resultFormatCodes = Objects.requireNonNull(resultFormatCodes, "resultFormatCodes");
        for (Integer resultFormatCode : resultFormatCodes) {
            Objects.requireNonNull(resultFormatCode, "resultFormatCode");
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
     * Destination portal name, empty for the unnamed portal.
     */
    public String getDestinationPortalName() {
        return destinationPortalName;
    }

    /**
     * Source prepared statement name, empty for the unnamed statement.
     */
    public String getSourcePreparedStatementName() {
        return sourcePreparedStatementName;
    }

    /**
     * Format codes for parameter values.
     */
    public ImmutableList<Integer> getParameterFormatCodes() {
        return parameterFormatCodes;
    }

    /**
     * Parameter values supplied for the prepared statement.
     */
    public ImmutableList<NullableValue> getParameterValues() {
        return parameterValues;
    }

    /**
     * Format codes requested for result columns.
     */
    public ImmutableList<Integer> getResultFormatCodes() {
        return resultFormatCodes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                destinationPortalName,
                sourcePreparedStatementName,
                parameterFormatCodes,
                parameterValues,
                resultFormatCodes);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BindMessage)) {
            return false;
        }
        BindMessage otherBind = (BindMessage) other;
        return destinationPortalName.equals(otherBind.destinationPortalName)
                && sourcePreparedStatementName.equals(otherBind.sourcePreparedStatementName)
                && parameterFormatCodes.equals(otherBind.parameterFormatCodes)
                && parameterValues.equals(otherBind.parameterValues)
                && resultFormatCodes.equals(otherBind.resultFormatCodes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("destinationPortalName", destinationPortalName)
                .add("sourcePreparedStatementName", sourcePreparedStatementName)
                .add("parameterFormatCodes", parameterFormatCodes)
                .add("parameterValues", parameterValues)
                .add("resultFormatCodes", resultFormatCodes)
                .build();
    }

}
