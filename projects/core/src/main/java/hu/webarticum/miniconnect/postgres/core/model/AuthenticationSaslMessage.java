package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request listing supported SASL mechanisms. */
public final class AuthenticationSaslMessage implements AuthenticationMessage {

    public static final int AUTHENTICATION_CODE = 10;

    private final ImmutableList<String> mechanismNames;

    public AuthenticationSaslMessage(ImmutableList<String> mechanismNames) {
        this.mechanismNames = Objects.requireNonNull(mechanismNames, "mechanismNames");
        mechanismNames.forEach(v -> Objects.requireNonNull(v, "mechanismName"));
    }

    /** Authentication request code carried in the message. */
    @Override
    public int authenticationCode() {
        return AUTHENTICATION_CODE;
    }

    /** SASL mechanism names in server preference order. */
    public ImmutableList<String> mechanismNames() {
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
        } else if (!(other instanceof AuthenticationSaslMessage)) {
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
