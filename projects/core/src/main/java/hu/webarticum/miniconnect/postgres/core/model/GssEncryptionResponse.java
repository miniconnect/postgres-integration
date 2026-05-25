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

    @Override
    public int hashCode() {
        return Boolean.hashCode(supported);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof GssEncryptionResponse)) {
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
