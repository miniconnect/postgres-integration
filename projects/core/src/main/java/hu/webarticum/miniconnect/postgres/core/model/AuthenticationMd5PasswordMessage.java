package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring an MD5-encrypted password. */
public final class AuthenticationMd5PasswordMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'R';

    public static final int AUTHENTICATION_CODE = 5;

    private final ByteString salt;

    public AuthenticationMd5PasswordMessage(ByteString salt) {
        this.salt = Objects.requireNonNull(salt, "salt");
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

    /** Salt to use when encrypting the password. */
    public ByteString salt() {
        return salt;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(salt);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticationMd5PasswordMessage)) {
            return false;
        }
        AuthenticationMd5PasswordMessage otherAuthenticationMd5Password =
                (AuthenticationMd5PasswordMessage) other;
        return Objects.equals(salt, otherAuthenticationMd5Password.salt);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("salt", salt)
                .build();
    }

}
