package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Startup request asking whether GSSAPI encryption is supported. */
public final class GssEncryptionRequest implements InitialMessage, FrontendMessage {

    public static final int REQUEST_CODE = 80877104;

    @Override
    public int hashCode() {
        return GssEncryptionRequest.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof GssEncryptionRequest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
