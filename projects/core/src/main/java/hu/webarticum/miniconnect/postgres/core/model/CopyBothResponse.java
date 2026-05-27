package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Start Copy Both response used for streaming replication. */
public final class CopyBothResponse implements TaggedMessage, BackendMessage {

    private final FormatCode overallFormatCode;

    private final ImmutableList<FormatCode> columnFormatCodes;

    public CopyBothResponse(FormatCode overallFormatCode, ImmutableList<FormatCode> columnFormatCodes) {
        this.overallFormatCode = Objects.requireNonNull(overallFormatCode, "overallFormatCode");
        this.columnFormatCodes = Objects.requireNonNull(columnFormatCodes, "columnFormatCodes");
        int columnFormatCodeCount = columnFormatCodes.size();
        if (columnFormatCodeCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "columnFormatCodes size must be at most 65535, but was %d",
                    columnFormatCodeCount));
        }
        columnFormatCodes.forEach(v -> Objects.requireNonNull(v, "columnFormatCode"));
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
        } else if (!(other instanceof CopyBothResponse)) {
            return false;
        }
        CopyBothResponse otherCopyBothResponse = (CopyBothResponse) other;
        return
                overallFormatCode == otherCopyBothResponse.overallFormatCode &&
                columnFormatCodes.equals(otherCopyBothResponse.columnFormatCodes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("overallFormatCode", overallFormatCode)
                .add("columnFormatCodes", columnFormatCodes)
                .build();
    }

}
