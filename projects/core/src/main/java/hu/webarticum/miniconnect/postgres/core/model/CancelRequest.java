package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Request to cancel work running in a target backend. */
public final class CancelRequest implements InitialMessage, FrontendMessage {

    public static final int REQUEST_CODE = 80877102;

    private final int processId;

    private final ByteString secretKey;

    public CancelRequest(int processId, ByteString secretKey) {
        this.processId = processId;
        this.secretKey = Objects.requireNonNull(secretKey, "secretKey");
    }

    /** Process ID of the target backend. */
    public int processId() {
        return processId;
    }

    /** Secret key for the target backend. */
    public ByteString secretKey() {
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
        if (!(other instanceof CancelRequest)) {
            return false;
        }
        CancelRequest otherCancelRequest = (CancelRequest) other;
        return processId == otherCancelRequest.processId
                && Objects.equals(secretKey, otherCancelRequest.secretKey);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("processId", processId)
                .add("secretKey", secretKey)
                .build();
    }

}
