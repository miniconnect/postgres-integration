package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Frontend COPY-failure indicator with an error message. */
public final class CopyFailMessage implements TaggedMessage, FrontendMessage {

    private final CString message;

    public CopyFailMessage(CString message) {
        this.message = Objects.requireNonNull(message, "message");
    }

    /** Error message explaining the COPY failure. */
    public CString message() {
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
        } else if (!(other instanceof CopyFailMessage)) {
            return false;
        }
        CopyFailMessage otherCopyFail = (CopyFailMessage) other;
        return message.equals(otherCopyFail.message);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("messageByteLength", message.bytes().length())
                .build();
    }

}
