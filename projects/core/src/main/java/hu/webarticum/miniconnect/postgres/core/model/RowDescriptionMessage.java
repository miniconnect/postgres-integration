package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Description of fields returned by rows.
 */
public final class RowDescriptionMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'T';

    private final ImmutableList<FieldDescription> fields;

    public RowDescriptionMessage(ImmutableList<FieldDescription> fields) {
        this.fields = Objects.requireNonNull(fields, "fields");
        for (FieldDescription field : fields) {
            Objects.requireNonNull(field, "field");
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
     * Descriptions of the fields in returned rows.
     */
    public ImmutableList<FieldDescription> getFields() {
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
        }
        if (!(other instanceof RowDescriptionMessage)) {
            return false;
        }
        RowDescriptionMessage otherRowDescription = (RowDescriptionMessage) other;
        return fields.equals(otherRowDescription.fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fields", fields)
                .build();
    }

}
