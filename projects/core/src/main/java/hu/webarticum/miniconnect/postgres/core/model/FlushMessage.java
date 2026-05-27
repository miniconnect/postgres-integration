package hu.webarticum.miniconnect.postgres.core.model;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** FlushMessage command requesting pending backend output. */
public final class FlushMessage implements TaggedMessage, FrontendMessage {

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
