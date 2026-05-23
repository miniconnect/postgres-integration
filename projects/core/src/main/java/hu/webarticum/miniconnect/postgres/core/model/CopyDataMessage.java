package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** COPY data chunk in a COPY data stream. */
public final class CopyDataMessage implements TaggedMessage, FrontendMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'd';

    private final ByteString data;

    public CopyDataMessage(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** COPY stream data bytes. */
    public ByteString data() {
        return data;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CopyDataMessage)) {
            return false;
        }
        CopyDataMessage otherCopyData = (CopyDataMessage) other;
        return Objects.equals(data, otherCopyData.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("data", data)
                .build();
    }

}
