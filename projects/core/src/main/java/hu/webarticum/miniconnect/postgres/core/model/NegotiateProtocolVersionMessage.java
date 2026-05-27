package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Protocol-version negotiation response. */
public final class NegotiateProtocolVersionMessage implements TaggedMessage, BackendMessage {

    private final int newestSupportedMinorVersion;

    private final ImmutableList<CString> unrecognizedProtocolOptions;

    public NegotiateProtocolVersionMessage(
            int newestSupportedMinorVersion,
            ImmutableList<CString> unrecognizedProtocolOptions) {
        this.newestSupportedMinorVersion = newestSupportedMinorVersion;
        this.unrecognizedProtocolOptions =
                Objects.requireNonNull(unrecognizedProtocolOptions, "unrecognizedProtocolOptions");
        unrecognizedProtocolOptions.forEach(v -> Objects.requireNonNull(v, "unrecognizedProtocolOption"));
    }

    /** Newest protocol minor version supported by the backend. */
    public int newestSupportedMinorVersion() {
        return newestSupportedMinorVersion;
    }

    /** Protocol options not recognized by the backend. */
    public ImmutableList<CString> unrecognizedProtocolOptions() {
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
        } else if (!(other instanceof NegotiateProtocolVersionMessage)) {
            return false;
        }
        NegotiateProtocolVersionMessage otherNegotiateProtocolVersion =
                (NegotiateProtocolVersionMessage) other;
        return
                newestSupportedMinorVersion == otherNegotiateProtocolVersion.newestSupportedMinorVersion &&
                unrecognizedProtocolOptions.equals(otherNegotiateProtocolVersion.unrecognizedProtocolOptions);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("newestSupportedMinorVersion", newestSupportedMinorVersion)
                .add("unrecognizedProtocolOptions", unrecognizedProtocolOptions)
                .build();
    }

}
