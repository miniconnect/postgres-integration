package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Startup packet carrying protocol version and connection parameters. */
public final class StartupMessage implements InitialMessage, FrontendMessage {

    private final ProtocolVersion protocolVersion;

    private final ImmutableList<StartupParameter> parameters;

    public StartupMessage(ProtocolVersion protocolVersion, ImmutableList<StartupParameter> parameters) {
        this.protocolVersion = Objects.requireNonNull(protocolVersion, "protocolVersion");
        this.parameters = Objects.requireNonNull(parameters, "parameters");
        parameters.forEach(v -> Objects.requireNonNull(v, "parameter"));
    }

    /** Protocol version requested by the frontend. */
    public ProtocolVersion protocolVersion() {
        return protocolVersion;
    }

    /** Startup parameter name-value pairs. */
    public ImmutableList<StartupParameter> parameters() {
        return parameters;
    }

    /** Value of a single startup parameter. */
    public String parameter(String name) {
        Objects.requireNonNull(name, "name");
        for (int i = parameters.size() - 1; i >= 0; i--) {
            StartupParameter parameter = parameters.get(i);
            if (parameter.name().equals(name)) {
                return parameter.value();
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocolVersion, parameters);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof StartupMessage)) {
            return false;
        }
        StartupMessage otherStartupMessage = (StartupMessage) other;
        return
                protocolVersion.equals(otherStartupMessage.protocolVersion) &&
                parameters.equals(otherStartupMessage.parameters);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("protocolVersion", protocolVersion)
                .add("parameters", parameters)
                .build();
    }

}
