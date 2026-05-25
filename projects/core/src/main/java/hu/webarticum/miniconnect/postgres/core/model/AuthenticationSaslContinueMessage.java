package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request carrying a SASL challenge. */
public final class AuthenticationSaslContinueMessage implements AuthenticationMessage {

    public static final int AUTHENTICATION_CODE = 11;

    private final ByteString data;

    public AuthenticationSaslContinueMessage(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /** Authentication request code carried in the message. */
    @Override
    public int authenticationCode() {
        return AUTHENTICATION_CODE;
    }

    /** SASL challenge data for the selected mechanism. */
    public ByteString data() {
        return data;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof AuthenticationSaslContinueMessage)) {
            return false;
        }
        AuthenticationSaslContinueMessage otherAuthenticationSaslContinue = (AuthenticationSaslContinueMessage) other;
        return Objects.equals(data, otherAuthenticationSaslContinue.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("data", data)
                .build();
    }

}
