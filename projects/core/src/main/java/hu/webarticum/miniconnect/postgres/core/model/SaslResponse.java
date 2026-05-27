package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** SASL response data for an authentication exchange. */
public final class SaslResponse implements TaggedMessage, FrontendMessage {

    private final ByteString data;

    public SaslResponse(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
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
                .add("dataLength", data.length())
                .build();
    }

}
