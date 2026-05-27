package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Runtime parameter status reported by the backend. */
public final class ParameterStatusMessage implements TaggedMessage, BackendMessage {

    private final CString name;

    private final CString value;

    public ParameterStatusMessage(CString name, CString value) {
        this.name = Objects.requireNonNull(name, "name");
        this.value = Objects.requireNonNull(value, "value");
    }

    /** Runtime parameter name. */
    public CString name() {
        return name;
    }

    /** Runtime parameter value. */
    public CString value() {
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
        } else if (!(other instanceof ParameterStatusMessage)) {
            return false;
        }
        ParameterStatusMessage otherParameterStatus = (ParameterStatusMessage) other;
        return
                name.equals(otherParameterStatus.name) &&
                value.equals(otherParameterStatus.value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("valueByteLength", value.bytes().length())
                .build();
    }

}
