package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Cancellation key data for later CancelRequest messages. */
public final class BackendKeyDataMessage implements TaggedMessage, BackendMessage {

    private final int processId;

    private final CancellationKey secretKey;

    /**
     * Creates a backend cancellation key message.
     *
     * <p>The secret key is represented by {@link CancellationKey}, which enforces the protocol length constraints.
     */
    public BackendKeyDataMessage(int processId, CancellationKey secretKey) {
        this.processId = processId;
        this.secretKey = Objects.requireNonNull(secretKey, "secretKey");
    }

    /** Process ID of this backend. */
    public int processId() {
        return processId;
    }

    /**
     * Secret key of this backend.
     */
    public CancellationKey secretKey() {
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
                .add("secretKeyLength", secretKey.bytes().length())
                .build();
    }

}
