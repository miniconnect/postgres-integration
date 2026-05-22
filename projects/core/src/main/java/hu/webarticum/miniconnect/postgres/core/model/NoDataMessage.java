package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Indicator that no row description data is available.
 */
public final class NoDataMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'n';

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public int hashCode() {
        return NoDataMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NoDataMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
