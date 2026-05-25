package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Cancellation key data for later CancelRequest messages. */
public final class BackendKeyDataMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'K';

    private final int processId;

    private final ByteString secretKey;

    public BackendKeyDataMessage(int processId, ByteString secretKey) {
        this.processId = processId;
        this.secretKey = Objects.requireNonNull(secretKey, "secretKey");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Process ID of this backend. */
    public int processId() {
        return processId;
    }

    /** Secret key of this backend. */
    public ByteString secretKey() {
        return secretKey;
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, secretKey);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof BackendKeyDataMessage)) {
            return false;
        }
        BackendKeyDataMessage otherBackendKeyData = (BackendKeyDataMessage) other;
        return
                processId == otherBackendKeyData.processId &&
                Objects.equals(secretKey, otherBackendKeyData.secretKey);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("processId", processId)
                .add("secretKey", secretKey)
                .build();
    }

}
