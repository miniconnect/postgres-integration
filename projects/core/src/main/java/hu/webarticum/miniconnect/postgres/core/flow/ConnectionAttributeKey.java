package hu.webarticum.miniconnect.postgres.core.flow;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Typed key for connection-scoped application attributes. */
public final class ConnectionAttributeKey<T> {

    private final String name;

    private ConnectionAttributeKey(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public static <T> ConnectionAttributeKey<T> of(String name) {
        return new ConnectionAttributeKey<T>(name);
    }

    /** Attribute key name for diagnostics. */
    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConnectionAttributeKey)) {
            return false;
        }
        ConnectionAttributeKey<?> otherConnectionAttributeKey = (ConnectionAttributeKey<?>) other;
        return name.equals(otherConnectionAttributeKey.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .build();
    }

}
