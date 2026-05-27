package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Error response made of protocol fields. */
public final class ErrorResponse implements TaggedMessage, BackendMessage {

    private final ImmutableList<ResponseField> fields;

    public ErrorResponse(ImmutableList<ResponseField> fields) {
        this.fields = Objects.requireNonNull(fields, "fields");
        fields.forEach(v -> Objects.requireNonNull(v, "field"));
    }

    /** Error fields in protocol order. */
    public ImmutableList<ResponseField> fields() {
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
        } else if (!(other instanceof ErrorResponse)) {
            return false;
        }
        ErrorResponse otherErrorResponse = (ErrorResponse) other;
        return fields.equals(otherErrorResponse.fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fieldCount", fields.size())
                .build();
    }

}
