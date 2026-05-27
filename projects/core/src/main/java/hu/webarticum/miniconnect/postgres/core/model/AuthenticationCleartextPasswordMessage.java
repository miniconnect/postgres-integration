package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring a clear-text password. */
public final class AuthenticationCleartextPasswordMessage implements AuthenticationMessage {

    @Override
    public int hashCode() {
        return AuthenticationCleartextPasswordMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AuthenticationCleartextPasswordMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
