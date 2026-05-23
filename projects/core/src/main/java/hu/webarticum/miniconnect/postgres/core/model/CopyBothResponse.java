package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Start Copy Both response used for streaming replication. */
public final class CopyBothResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'W';

    private final int overallFormatCode;

    private final ImmutableList<Integer> columnFormatCodes;

    public CopyBothResponse(int overallFormatCode, ImmutableList<Integer> columnFormatCodes) {
        this.overallFormatCode = overallFormatCode;
        this.columnFormatCodes = Objects.requireNonNull(columnFormatCodes, "columnFormatCodes");
        for (Integer columnFormatCode : columnFormatCodes) {
            Objects.requireNonNull(columnFormatCode, "columnFormatCode");
        }
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Overall COPY format code. */
    public int overallFormatCode() {
        return overallFormatCode;
    }

    /** Format codes for each copied column. */
    public ImmutableList<Integer> columnFormatCodes() {
        return columnFormatCodes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(overallFormatCode, columnFormatCodes);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CopyBothResponse)) {
            return false;
        }
        CopyBothResponse otherCopyBothResponse = (CopyBothResponse) other;
        return overallFormatCode == otherCopyBothResponse.overallFormatCode
                && columnFormatCodes.equals(otherCopyBothResponse.columnFormatCodes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("overallFormatCode", overallFormatCode)
                .add("columnFormatCodes", columnFormatCodes)
                .build();
    }

}
