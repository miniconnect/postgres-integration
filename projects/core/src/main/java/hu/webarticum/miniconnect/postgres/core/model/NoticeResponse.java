package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Notice response made of protocol fields. */
public final class NoticeResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'N';

    private final ImmutableList<ResponseField> fields;

    public NoticeResponse(ImmutableList<ResponseField> fields) {
        this.fields = Objects.requireNonNull(fields, "fields");
        fields.forEach(v -> Objects.requireNonNull(v, "field"));
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Notice fields in protocol order. */
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
        } else if (!(other instanceof NoticeResponse)) {
            return false;
        }
        NoticeResponse otherNoticeResponse = (NoticeResponse) other;
        return fields.equals(otherNoticeResponse.fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fields", fields)
                .build();
    }

}
