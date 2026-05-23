package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Frontend request to close the connection. */
public final class TerminateMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'X';

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public int hashCode() {
        return TerminateMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TerminateMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
