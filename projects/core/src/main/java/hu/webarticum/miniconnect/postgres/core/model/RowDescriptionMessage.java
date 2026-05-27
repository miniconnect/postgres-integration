package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Description of fields returned by rows. */
public final class RowDescriptionMessage implements TaggedMessage, BackendMessage {

    private final ImmutableList<FieldDescription> fields;

    public RowDescriptionMessage(ImmutableList<FieldDescription> fields) {
        this.fields = Objects.requireNonNull(fields, "fields");
        int fieldCount = fields.size();
        if (fieldCount > 0xFFFF) {
            throw new IllegalArgumentException(String.format(
                    "fields size must be at most 65535, but was %d",
                    fieldCount));
        }
        fields.forEach(v -> Objects.requireNonNull(v, "field"));
    }

    /** Descriptions of the fields in returned rows. */
    public ImmutableList<FieldDescription> fields() {
        return fields;
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof RowDescriptionMessage)) {
            return false;
        }
        RowDescriptionMessage otherRowDescription = (RowDescriptionMessage) other;
        return fields.equals(otherRowDescription.fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fieldCount", fields.size())
                .build();
    }

}
