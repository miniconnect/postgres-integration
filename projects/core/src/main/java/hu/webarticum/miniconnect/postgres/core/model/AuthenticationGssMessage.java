package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring GSSAPI. */
public final class AuthenticationGssMessage implements AuthenticationMessage {

    @Override
    public int hashCode() {
        return AuthenticationGssMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AuthenticationGssMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
