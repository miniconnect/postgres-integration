package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** SASL response data for an authentication exchange. */
public final class SaslResponse implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'p';

    private final ByteString data;

    public SaslResponse(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** SASL response data bytes. */
    public ByteString data() {
        return data;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof SaslResponse)) {
            return false;
        }
        SaslResponse otherSaslResponse = (SaslResponse) other;
        return Objects.equals(data, otherSaslResponse.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("data", data)
                .build();
    }

}
