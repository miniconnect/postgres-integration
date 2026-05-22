package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Cancellation key data for later CancelRequest messages.
 */
public final class BackendKeyDataMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'K';

    private final int processId;

    private final ByteString secretKey;

    public BackendKeyDataMessage(int processId, ByteString secretKey) {
        this.processId = processId;
        this.secretKey = Objects.requireNonNull(secretKey, "secretKey");
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Process ID of this backend.
     */
    public int getProcessId() {
        return processId;
    }

    /**
     * Secret key of this backend.
     */
    public ByteString getSecretKey() {
        return secretKey;
    }

    @Override
    public int hashCode() {
        return 31 * processId + Objects.hashCode(secretKey);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BackendKeyDataMessage)) {
            return false;
        }
        BackendKeyDataMessage otherBackendKeyData = (BackendKeyDataMessage) other;
        return processId == otherBackendKeyData.processId
                && Objects.equals(secretKey, otherBackendKeyData.secretKey);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("processId", processId)
                .add("secretKey", secretKey)
                .build();
    }

}
