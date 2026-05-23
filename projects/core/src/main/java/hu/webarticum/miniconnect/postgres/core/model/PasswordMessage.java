package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Password response for password-based authentication. */
public final class PasswordMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'p';

    private final String password;

    public PasswordMessage(String password) {
        this.password = Objects.requireNonNull(password, "password");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Password or password-response string. */
    public String password() {
        return password;
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PasswordMessage)) {
            return false;
        }
        PasswordMessage otherPasswordMessage = (PasswordMessage) other;
        return password.equals(otherPasswordMessage.password);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("password", "***")
                .build();
    }

}
