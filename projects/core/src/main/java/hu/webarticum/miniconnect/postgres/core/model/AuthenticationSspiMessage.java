package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring SSPI. */
public final class AuthenticationSspiMessage implements AuthenticationMessage {

    @Override
    public int hashCode() {
        return AuthenticationSspiMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AuthenticationSspiMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
