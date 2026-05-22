package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Authentication request requiring Kerberos V5.
 */
public final class AuthenticationKerberosV5Message implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'R';

    public static final int AUTHENTICATION_CODE = 2;

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Authentication request code carried in the message.
     */
    public int getAuthenticationCode() {
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
