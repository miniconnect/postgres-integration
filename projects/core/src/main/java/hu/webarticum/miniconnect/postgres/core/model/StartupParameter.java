package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Single startup parameter name/value pair sent by the frontend. */
public final class StartupParameter {

    private final String name;

    private final String value;

    public StartupParameter(String name, String value) {
        this.name = Objects.requireNonNull(name, "name");
        this.value = Objects.requireNonNull(value, "value");
    }

    /** Startup parameter name. */
    public String name() {
        return name;
    }

    /** Startup parameter value. */
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
        if (!(other instanceof StartupParameter)) {
            return false;
        }
        StartupParameter otherStartupParameter = (StartupParameter) other;
        return name.equals(otherStartupParameter.name)
                && value.equals(otherStartupParameter.value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("value", value)
                .build();
    }

}
