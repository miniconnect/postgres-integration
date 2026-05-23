package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request carrying GSSAPI or SSPI data. */
public final class AuthenticationGssContinueMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'R';

    public static final int AUTHENTICATION_CODE = 8;

    private final ByteString data;

    public AuthenticationGssContinueMessage(ByteString data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Authentication request code carried in the message. */
    public int authenticationCode() {
        return AUTHENTICATION_CODE;
    }

    /** GSSAPI or SSPI authentication data bytes. */
    public ByteString data() {
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
        if (!(other instanceof AuthenticationGssContinueMessage)) {
            return false;
        }
        AuthenticationGssContinueMessage otherAuthenticationGssContinue =
                (AuthenticationGssContinueMessage) other;
        return Objects.equals(data, otherAuthenticationGssContinue.data);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("data", data)
                .build();
    }

}
