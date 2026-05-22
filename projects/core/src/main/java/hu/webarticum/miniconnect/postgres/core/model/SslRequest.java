package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Startup request asking whether SSL encryption is supported.
 */
public final class SslRequest implements InitialMessage, FrontendMessage {

    public static final int REQUEST_CODE = 80877103;

    @Override
    public int hashCode() {
        return SslRequest.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SslRequest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
