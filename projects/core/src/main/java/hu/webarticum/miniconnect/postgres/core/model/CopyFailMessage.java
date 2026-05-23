package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Frontend COPY-failure indicator with an error message. */
public final class CopyFailMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'f';

    private final String message;

    public CopyFailMessage(String message) {
        this.message = Objects.requireNonNull(message, "message");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Error message explaining the COPY failure. */
    public String message() {
        return message;
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CopyFailMessage)) {
            return false;
        }
        CopyFailMessage otherCopyFail = (CopyFailMessage) other;
        return message.equals(otherCopyFail.message);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("message", message)
                .build();
    }

}
