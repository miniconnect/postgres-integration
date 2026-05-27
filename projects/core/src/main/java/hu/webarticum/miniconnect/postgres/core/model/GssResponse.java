package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Frontend GSSAPI or SSPI authentication data. */
public final class GssResponse implements TaggedMessage, FrontendMessage {

    private final ByteString data;

    public GssResponse(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /** GSSAPI or SSPI authentication data bytes. */
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
        } else if (!(other instanceof GssResponse)) {
            return false;
        }
        GssResponse otherGSSResponse = (GssResponse) other;
        return Objects.equals(data, otherGSSResponse.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dataLength", data.length())
                .build();
    }

}
