package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Start Copy In response requiring frontend COPY data. */
public final class CopyInResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'G';

    private final FormatCode overallFormatCode;

    private final ImmutableList<FormatCode> columnFormatCodes;

    public CopyInResponse(FormatCode overallFormatCode, ImmutableList<FormatCode> columnFormatCodes) {
        this.overallFormatCode = Objects.requireNonNull(overallFormatCode, "overallFormatCode");
        this.columnFormatCodes = Objects.requireNonNull(columnFormatCodes, "columnFormatCodes");
        for (FormatCode columnFormatCode : columnFormatCodes) {
            Objects.requireNonNull(columnFormatCode, "columnFormatCode");
        }
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Overall COPY format code. */
    public FormatCode overallFormatCode() {
        return overallFormatCode;
    }

    /** Format codes for each copied column. */
    public ImmutableList<FormatCode> columnFormatCodes() {
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
        if (!(other instanceof CopyInResponse)) {
            return false;
        }
        CopyInResponse otherCopyInResponse = (CopyInResponse) other;
        return overallFormatCode == otherCopyInResponse.overallFormatCode
                && columnFormatCodes.equals(otherCopyInResponse.columnFormatCodes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("overallFormatCode", overallFormatCode)
                .add("columnFormatCodes", columnFormatCodes)
                .build();
    }

}
