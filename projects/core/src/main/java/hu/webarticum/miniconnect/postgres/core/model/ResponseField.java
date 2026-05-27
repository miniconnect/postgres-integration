package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Single field in an error or notice response. */
public final class ResponseField {

    private final char type;

    private final CString value;

    public ResponseField(ResponseFieldType type, CString value) {
        this(Objects.requireNonNull(type, "type").code(), value);
    }

    public ResponseField(char type, CString value) {
        if (type == '\0' || type > 0xFF) {
            throw new IllegalArgumentException(String.format(
                    "type must be a non-zero one-byte code, but was %d",
                    Integer.valueOf(type)));
        }
        this.type = type;
        this.value = Objects.requireNonNull(value, "value");
    }

    /** Non-zero one-byte field type code as defined by the PostgreSQL protocol. */
    public char type() {
        return type;
    }

    /** Field value. */
    public CString value() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Character.valueOf(type), value);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ResponseField)) {
            return false;
        }
        ResponseField otherResponseField = (ResponseField) other;
        return
                type == otherResponseField.type &&
                value.equals(otherResponseField.value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", Character.valueOf(type))
                .add("valueByteLength", value.bytes().length())
                .build();
    }

}
