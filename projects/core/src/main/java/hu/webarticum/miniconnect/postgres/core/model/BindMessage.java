package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** BindMessage command that creates a portal from a prepared statement. */
public final class BindMessage implements TaggedMessage, FrontendMessage {

    private final CString destinationPortalName;

    private final CString sourcePreparedStatementName;

    private final ImmutableList<FormatCode> parameterFormatCodes;

    private final ImmutableList<NullableValue> parameterValues;

    private final ImmutableList<FormatCode> resultFormatCodes;

    public BindMessage(
            CString destinationPortalName,
            CString sourcePreparedStatementName,
            ImmutableList<FormatCode> parameterFormatCodes,
            ImmutableList<NullableValue> parameterValues,
            ImmutableList<FormatCode> resultFormatCodes) {
        this.destinationPortalName = Objects.requireNonNull(destinationPortalName, "destinationPortalName");
        this.sourcePreparedStatementName = Objects.requireNonNull(sourcePreparedStatementName, "sourcePreparedStatementName");
        this.parameterFormatCodes = Objects.requireNonNull(parameterFormatCodes, "parameterFormatCodes");
        int parameterFormatCodeCount = parameterFormatCodes.size();
        if (parameterFormatCodeCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "parameterFormatCodes size must be at most 65535, but was %d",
                    parameterFormatCodeCount));
        }
        parameterFormatCodes.forEach(v -> Objects.requireNonNull(v, "parameterFormatCode"));
        this.parameterValues = Objects.requireNonNull(parameterValues, "parameterValues");
        int parameterValueCount = parameterValues.size();
        if (parameterValueCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "parameterValues size must be at most 65535, but was %d",
                    parameterValueCount));
        }
        parameterValues.forEach(v -> Objects.requireNonNull(v, "parameterValue"));
        if (
                parameterFormatCodeCount != 0 &&
                parameterFormatCodeCount != 1 &&
                parameterFormatCodeCount != parameterValueCount) {
            throw new IllegalArgumentException(String.format(
                    "parameterFormatCodes size must be 0, 1, or equal to parameterValues size (%d), but was %d",
                    parameterValueCount, parameterFormatCodeCount));
        }
        this.resultFormatCodes = Objects.requireNonNull(resultFormatCodes, "resultFormatCodes");
        int resultFormatCodeCount = resultFormatCodes.size();
        if (resultFormatCodeCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "resultFormatCodes size must be at most 65535, but was %d",
                    resultFormatCodeCount));
        }
        resultFormatCodes.forEach(v -> Objects.requireNonNull(v, "resultFormatCode"));
    }

    /** Destination portal name, empty for the unnamed portal. */
    public CString destinationPortalName() {
        return destinationPortalName;
    }

    /** Source prepared statement name, empty for the unnamed statement. */
    public CString sourcePreparedStatementName() {
        return sourcePreparedStatementName;
    }

    /** Format codes for parameter values. */
    public ImmutableList<FormatCode> parameterFormatCodes() {
        return parameterFormatCodes;
    }

    /** Parameter values supplied for the prepared statement. */
    public ImmutableList<NullableValue> parameterValues() {
        return parameterValues;
    }

    /** Format codes requested for result columns. */
    public ImmutableList<FormatCode> resultFormatCodes() {
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
        } else if (!(other instanceof BindMessage)) {
            return false;
        }
        BindMessage otherBind = (BindMessage) other;
        return
                destinationPortalName.equals(otherBind.destinationPortalName) &&
                sourcePreparedStatementName.equals(otherBind.sourcePreparedStatementName) &&
                parameterFormatCodes.equals(otherBind.parameterFormatCodes) &&
                parameterValues.equals(otherBind.parameterValues) &&
                resultFormatCodes.equals(otherBind.resultFormatCodes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("destinationPortalName", destinationPortalName)
                .add("sourcePreparedStatementName", sourcePreparedStatementName)
                .add("parameterFormatCodeCount", parameterFormatCodes.size())
                .add("parameterValueCount", parameterValues.size())
                .add("resultFormatCodeCount", resultFormatCodes.size())
                .build();
    }

}
