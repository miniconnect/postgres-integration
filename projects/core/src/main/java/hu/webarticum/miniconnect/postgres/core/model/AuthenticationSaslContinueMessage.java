package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Authentication request carrying a SASL challenge.
 */
public final class AuthenticationSaslContinueMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'R';

    public static final int AUTHENTICATION_CODE = 11;

    private final ByteString data;

    public AuthenticationSaslContinueMessage(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
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
     * SASL challenge data for the selected mechanism.
     */
    public ByteString getData() {
        return data;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticationSaslContinueMessage)) {
            return false;
        }
        AuthenticationSaslContinueMessage otherAuthenticationSaslContinue =
                (AuthenticationSaslContinueMessage) other;
        return Objects.equals(data, otherAuthenticationSaslContinue.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("data", data)
                .build();
    }

}
