package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Authentication request requiring an MD5-encrypted password. */
public final class AuthenticationMd5PasswordMessage implements AuthenticationMessage {

    private final int salt;

    public AuthenticationMd5PasswordMessage(int salt) {
        this.salt = salt;
    }

    /** Salt to use when encrypting the password. */
    public int salt() {
        return salt;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(salt);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof AuthenticationMd5PasswordMessage)) {
            return false;
        }
        AuthenticationMd5PasswordMessage otherAuthenticationMd5Password = (AuthenticationMd5PasswordMessage) other;
        return salt == otherAuthenticationMd5Password.salt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("salt", salt)
                .build();
    }

}
