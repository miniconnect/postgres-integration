package hu.webarticum.miniconnect.postgres.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Startup packet carrying protocol version and connection parameters.
 */
public final class StartupMessage implements InitialMessage, FrontendMessage {

    public static final int PROTOCOL_VERSION_3_0 = 196608;

    public static final int PROTOCOL_VERSION_3_1 = 196609;

    public static final int PROTOCOL_VERSION_3_2 = 196610;

    private final int protocolVersion;

    private final ImmutableList<StartupParameter> parameters;

    public StartupMessage(int protocolVersion, ImmutableList<StartupParameter> parameters) {
        this.protocolVersion = protocolVersion;
        this.parameters = Objects.requireNonNull(parameters, "parameters");
        for (StartupParameter parameter : parameters) {
            Objects.requireNonNull(parameter, "parameter");
        }
    }

    public StartupMessage(int protocolVersion, Map<String, String> parameters) {
        this(protocolVersion, toParameters(parameters));
    }

    /**
     * Protocol version number requested by the frontend.
     */
    public int getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Major part of the requested protocol version.
     */
    public int getProtocolMajorVersion() {
        return (protocolVersion >>> 16) & 0xFFFF;
    }

    /**
     * Minor part of the requested protocol version.
     */
    public int getProtocolMinorVersion() {
        return protocolVersion & 0xFFFF;
    }

    /**
     * Startup parameter name-value pairs.
     */
    public ImmutableList<StartupParameter> getParameters() {
        return parameters;
    }

    /**
     * Value of a single startup parameter.
     */
    public String getParameter(String name) {
        Objects.requireNonNull(name, "name");
        for (int i = parameters.size() - 1; i >= 0; i--) {
            StartupParameter parameter = parameters.get(i);
            if (parameter.getName().equals(name)) {
                return parameter.getValue();
            }
        }
        return null;
    }

    private static ImmutableList<StartupParameter> toParameters(Map<String, String> parameters) {
        Objects.requireNonNull(parameters, "parameters");
        List<StartupParameter> resultBuilder = new ArrayList<StartupParameter>(parameters.size());
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String name = Objects.requireNonNull(entry.getKey(), "parameter name");
            String value = Objects.requireNonNull(entry.getValue(), "parameter value");
            resultBuilder.add(new StartupParameter(name, value));
        }
        return ImmutableList.fromCollection(resultBuilder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocolVersion, parameters);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StartupMessage)) {
            return false;
        }
        StartupMessage otherStartupMessage = (StartupMessage) other;
        return protocolVersion == otherStartupMessage.protocolVersion
                && parameters.equals(otherStartupMessage.parameters);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("protocolVersion", protocolVersion)
                .add("parameters", parameters)
                .build();
    }

}
