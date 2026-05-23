package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Runtime parameter status reported by the backend. */
public final class ParameterStatusMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'S';

    private final String name;

    private final String value;

    public ParameterStatusMessage(String name, String value) {
        this.name = Objects.requireNonNull(name, "name");
        this.value = Objects.requireNonNull(value, "value");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Runtime parameter name. */
    public String name() {
        return name;
    }

    /** Runtime parameter value. */
    public String value() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParameterStatusMessage)) {
            return false;
        }
        ParameterStatusMessage otherParameterStatus = (ParameterStatusMessage) other;
        return name.equals(otherParameterStatus.name)
                && value.equals(otherParameterStatus.value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("value", value)
                .build();
    }

}
