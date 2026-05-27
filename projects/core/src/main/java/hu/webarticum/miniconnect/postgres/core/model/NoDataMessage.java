package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Indicator that no row description data is available. */
public final class NoDataMessage implements TaggedMessage, BackendMessage {

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
