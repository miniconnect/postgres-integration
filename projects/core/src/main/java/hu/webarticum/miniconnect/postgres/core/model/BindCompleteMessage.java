package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * BindMessage-complete indicator.
 */
public final class BindCompleteMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = '2';

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public int hashCode() {
        return BindCompleteMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BindCompleteMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
