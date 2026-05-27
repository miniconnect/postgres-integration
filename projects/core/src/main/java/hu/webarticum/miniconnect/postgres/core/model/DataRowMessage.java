package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Data row containing column values. */
public final class DataRowMessage implements TaggedMessage, BackendMessage {

    private final ImmutableList<NullableValue> columnValues;

    public DataRowMessage(ImmutableList<NullableValue> columnValues) {
        this.columnValues = Objects.requireNonNull(columnValues, "columnValues");
        int columnValueCount = columnValues.size();
        if (columnValueCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "columnValues size must be at most 65535, but was %d",
                    columnValueCount));
        }
        columnValues.forEach(v -> Objects.requireNonNull(v, "columnValue"));
    }

    /** Column values in this row. */
    public ImmutableList<NullableValue> columnValues() {
        return columnValues;
    }

    @Override
    public int hashCode() {
        return columnValues.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof DataRowMessage)) {
            return false;
        }
        DataRowMessage otherDataRow = (DataRowMessage) other;
        return columnValues.equals(otherDataRow.columnValues);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("columnValueCount", columnValues.size())
                .build();
    }

}
