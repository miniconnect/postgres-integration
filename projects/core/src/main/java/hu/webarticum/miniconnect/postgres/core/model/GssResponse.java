package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Frontend GSSAPI or SSPI authentication data.
 */
public final class GssResponse implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'p';

    private final ByteString data;

    public GssResponse(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * GSSAPI or SSPI authentication data bytes.
     */
    public ByteString getData() {
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
        if (!(other instanceof GssResponse)) {
            return false;
        }
        GssResponse otherGSSResponse = (GssResponse) other;
        return Objects.equals(data, otherGSSResponse.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("data", data)
                .build();
    }

}
