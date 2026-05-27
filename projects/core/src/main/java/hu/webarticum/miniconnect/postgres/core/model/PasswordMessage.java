package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Password response for password-based authentication. */
public final class PasswordMessage implements TaggedMessage, FrontendMessage {

    private final CString password;

    public PasswordMessage(CString password) {
        this.password = Objects.requireNonNull(password, "password");
    }

    /** Password or password-response string. */
    public CString password() {
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
        } else if (!(other instanceof PasswordMessage)) {
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
