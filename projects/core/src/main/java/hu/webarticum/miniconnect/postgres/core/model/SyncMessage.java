package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** SyncMessage command marking the end of an extended-query message series. */
public final class SyncMessage implements TaggedMessage, FrontendMessage {

    @Override
    public int hashCode() {
        return SyncMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SyncMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
