package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * FlushMessage command requesting pending backend output.
 */
public final class FlushMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'H';

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public int hashCode() {
        return FlushMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof FlushMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
