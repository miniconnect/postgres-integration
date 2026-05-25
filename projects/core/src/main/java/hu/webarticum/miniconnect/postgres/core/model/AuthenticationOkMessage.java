package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request indicating successful authentication. */
public final class AuthenticationOkMessage implements AuthenticationMessage {

    public static final int AUTHENTICATION_CODE = 0;

    /** Authentication request code carried in the message. */
    @Override
    public int authenticationCode() {
        return AUTHENTICATION_CODE;
    }

    @Override
    public int hashCode() {
        return AuthenticationOkMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AuthenticationOkMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
