package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Single-byte response to an SslRequest. */
public final class SslResponse implements InitialMessage, BackendMessage {

    private final boolean supported;

    public SslResponse(boolean supported) {
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
        } else if (!(other instanceof SslResponse)) {
            return false;
        }
        SslResponse otherSslResponse = (SslResponse) other;
        return supported == otherSslResponse.supported;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("supported", supported)
                .build();
    }

}
