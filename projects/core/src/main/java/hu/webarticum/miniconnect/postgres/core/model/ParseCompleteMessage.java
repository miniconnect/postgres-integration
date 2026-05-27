package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** ParseMessage-complete indicator. */
public final class ParseCompleteMessage implements TaggedMessage, BackendMessage {

    @Override
    public int hashCode() {
        return ParseCompleteMessage.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ParseCompleteMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }

}
