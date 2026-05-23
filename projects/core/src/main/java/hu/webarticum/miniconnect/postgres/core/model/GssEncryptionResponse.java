package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Single-byte response to a GssEncryptionRequest. */
public final class GssEncryptionResponse implements InitialMessage, BackendMessage {

    private final boolean supported;

    public GssEncryptionResponse(boolean supported) {
        this.supported = supported;
    }

    /** Whether the requested encryption mode is accepted. */
    public boolean isSupported() {
        return supported;
    }

    /** Single-byte response code sent on the wire. */
    public int responseCode() {
        return supported ? 'G' : 'N';
    }

    @Override
    public int hashCode() {
        return supported ? 1 : 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GssEncryptionResponse)) {
            return false;
        }
        GssEncryptionResponse otherGSSENCResponse = (GssEncryptionResponse) other;
        return supported == otherGSSENCResponse.supported;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("supported", supported)
                .build();
    }

}
