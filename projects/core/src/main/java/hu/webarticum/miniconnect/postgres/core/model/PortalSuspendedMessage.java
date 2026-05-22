package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Indicator that portal execution stopped before completion.
 */
public final class PortalSuspendedMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 's';

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public int hashCode() {
        return PortalSuspendedMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof PortalSuspendedMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
