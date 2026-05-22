package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Authentication request listing supported SASL mechanisms.
 */
public final class AuthenticationSaslMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'R';

    public static final int AUTHENTICATION_CODE = 10;

    private final ImmutableList<String> mechanismNames;

    public AuthenticationSaslMessage(ImmutableList<String> mechanismNames) {
        this.mechanismNames = Objects.requireNonNull(mechanismNames, "mechanismNames");
        for (String mechanismName : mechanismNames) {
            Objects.requireNonNull(mechanismName, "mechanismName");
        }
    }

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

    /**
     * SASL mechanism names in server preference order.
     */
    public ImmutableList<String> getMechanismNames() {
        return mechanismNames;
    }

    @Override
    public int hashCode() {
        return mechanismNames.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticationSaslMessage)) {
            return false;
        }
        AuthenticationSaslMessage otherAuthenticationSasl = (AuthenticationSaslMessage) other;
        return mechanismNames.equals(otherAuthenticationSasl.mechanismNames);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mechanismNames", mechanismNames)
                .build();
    }

}
