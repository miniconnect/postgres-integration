package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Close-complete indicator. */
public final class CloseCompleteMessage implements TaggedMessage, BackendMessage {

    @Override
    public int hashCode() {
        return CloseCompleteMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CloseCompleteMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
