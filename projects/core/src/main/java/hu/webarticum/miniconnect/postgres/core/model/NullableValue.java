package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Protocol value with an explicit SQL NULL marker. */
public final class NullableValue {

    private static final NullableValue NULL_VALUE = new NullableValue(true, ByteString.empty());

    private final boolean nullValue;

    private final ByteString bytes;

    private NullableValue(boolean nullValue, ByteString bytes) {
        this.nullValue = nullValue;
        this.bytes = Objects.requireNonNull(bytes, "bytes");
    }

    public static NullableValue nullValue() {
        return NULL_VALUE;
    }

    public static NullableValue of(ByteString bytes) {
        return new NullableValue(false, bytes);
    }

    public static NullableValue ofNullable(ByteString bytes) {
        return bytes == null ? nullValue() : of(bytes);
    }

    /** Whether the value is SQL NULL. */
    public boolean isNull() {
        return nullValue;
    }

    /** Value bytes in the selected format. <p>Callers must use {@link #isNull()} before accessing the payload, because SQL NULL has no payload. */
    public ByteString bytes() {
        if (nullValue) {
            throw new IllegalStateException("SQL NULL has no bytes");
        }
        return bytes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Boolean.valueOf(nullValue), bytes);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof NullableValue)) {
            return false;
        }
        NullableValue otherNullableValue = (NullableValue) other;
        return
                nullValue == otherNullableValue.nullValue &&
                bytes.equals(otherNullableValue.bytes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("isNull", nullValue)
                .add("bytes", bytes)
                .build();
    }

}
