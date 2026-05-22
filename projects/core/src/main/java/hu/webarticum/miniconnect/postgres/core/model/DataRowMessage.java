package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Data row containing column values.
 */
public final class DataRowMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'D';

    private final ImmutableList<NullableValue> columnValues;

    public DataRowMessage(ImmutableList<NullableValue> columnValues) {
        this.columnValues = Objects.requireNonNull(columnValues, "columnValues");
        for (NullableValue columnValue : columnValues) {
            Objects.requireNonNull(columnValue, "columnValue");
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
     * Column values in this row.
     */
    public ImmutableList<NullableValue> getColumnValues() {
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
        }
        if (!(other instanceof DataRowMessage)) {
            return false;
        }
        DataRowMessage otherDataRow = (DataRowMessage) other;
        return columnValues.equals(otherDataRow.columnValues);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("columnValues", columnValues)
                .build();
    }

}
