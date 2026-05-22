package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Initial SASL response with the selected mechanism.
 */
public final class SaslInitialResponse implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'p';

    private final String mechanismName;

    private final ByteString initialResponse;

    public SaslInitialResponse(String mechanismName, ByteString initialResponse) {
        this.mechanismName = Objects.requireNonNull(mechanismName, "mechanismName");
        this.initialResponse = initialResponse;
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Selected SASL authentication mechanism name.
     */
    public String getMechanismName() {
        return mechanismName;
    }

    /**
     * Whether SASL initial response data is present.
     */
    public boolean hasInitialResponse() {
        return initialResponse != null;
    }

    /**
     * Optional SASL initial response data.
     */
    public ByteString getInitialResponse() {
        return initialResponse;
    }

    @Override
    public int hashCode() {
        return 31 * mechanismName.hashCode() + Objects.hashCode(initialResponse);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SaslInitialResponse)) {
            return false;
        }
        SaslInitialResponse otherSaslInitialResponse = (SaslInitialResponse) other;
        return mechanismName.equals(otherSaslInitialResponse.mechanismName)
                && Objects.equals(initialResponse, otherSaslInitialResponse.initialResponse);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mechanismName", mechanismName)
                .add("initialResponse", initialResponse)
                .build();
    }

}
