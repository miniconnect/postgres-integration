package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** COPY data chunk in a COPY data stream. */
public final class CopyDataMessage implements TaggedMessage, FrontendMessage, BackendMessage {

    private final ByteString data;

    public CopyDataMessage(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /** COPY stream data bytes. */
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
        } else if (!(other instanceof CopyDataMessage)) {
            return false;
        }
        CopyDataMessage otherCopyData = (CopyDataMessage) other;
        return Objects.equals(data, otherCopyData.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dataLength", data.length())
                .build();
    }

}
