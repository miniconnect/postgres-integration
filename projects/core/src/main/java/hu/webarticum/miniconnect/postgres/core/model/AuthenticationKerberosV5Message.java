package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring Kerberos V5. */
public final class AuthenticationKerberosV5Message implements AuthenticationMessage {

    public static final int AUTHENTICATION_CODE = 2;

    /** Authentication request code carried in the message. */
    @Override
    public int authenticationCode() {
        return AUTHENTICATION_CODE;
    }

    @Override
    public int hashCode() {
        return AuthenticationKerberosV5Message.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AuthenticationKerberosV5Message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
