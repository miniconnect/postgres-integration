package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Protocol-version negotiation response.
 */
public final class NegotiateProtocolVersionMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'v';

    private final int newestSupportedMinorVersion;

    private final ImmutableList<String> unrecognizedProtocolOptions;

    public NegotiateProtocolVersionMessage(
            int newestSupportedMinorVersion,
            ImmutableList<String> unrecognizedProtocolOptions) {
        this.newestSupportedMinorVersion = newestSupportedMinorVersion;
        this.unrecognizedProtocolOptions =
                Objects.requireNonNull(unrecognizedProtocolOptions, "unrecognizedProtocolOptions");
        for (String unrecognizedProtocolOption : unrecognizedProtocolOptions) {
            Objects.requireNonNull(unrecognizedProtocolOption, "unrecognizedProtocolOption");
        }
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Newest protocol minor version supported by the backend.
     */
    public int getNewestSupportedMinorVersion() {
        return newestSupportedMinorVersion;
    }

    /**
     * Protocol options not recognized by the backend.
     */
    public ImmutableList<String> getUnrecognizedProtocolOptions() {
        return unrecognizedProtocolOptions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newestSupportedMinorVersion, unrecognizedProtocolOptions);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NegotiateProtocolVersionMessage)) {
            return false;
        }
        NegotiateProtocolVersionMessage otherNegotiateProtocolVersion =
                (NegotiateProtocolVersionMessage) other;
        return newestSupportedMinorVersion == otherNegotiateProtocolVersion.newestSupportedMinorVersion
                && unrecognizedProtocolOptions.equals(otherNegotiateProtocolVersion.unrecognizedProtocolOptions);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("newestSupportedMinorVersion", newestSupportedMinorVersion)
                .add("unrecognizedProtocolOptions", unrecognizedProtocolOptions)
                .build();
    }

}
