package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring SSPI. */
public final class AuthenticationSspiMessage implements AuthenticationMessage {

    public static final int AUTHENTICATION_CODE = 9;

    /** Authentication request code carried in the message. */
    @Override
    public int authenticationCode() {
        return AUTHENTICATION_CODE;
    }

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
