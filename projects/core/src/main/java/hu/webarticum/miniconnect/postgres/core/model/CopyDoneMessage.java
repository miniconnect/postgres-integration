package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** COPY-complete indicator. */
public final class CopyDoneMessage implements TaggedMessage, FrontendMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'c';

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public int hashCode() {
        return CopyDoneMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CopyDoneMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
