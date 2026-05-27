package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Initial SASL response with the selected mechanism. */
public final class SaslInitialResponse implements TaggedMessage, FrontendMessage {

    private final CString mechanismName;

    private final boolean hasPayload;

    private final ByteString payload;

    private SaslInitialResponse(CString mechanismName, boolean hasPayload, ByteString payload) {
        this.mechanismName = Objects.requireNonNull(mechanismName, "mechanismName");
        this.hasPayload = hasPayload;
        this.payload = Objects.requireNonNull(payload, "payload");
    }

    public static SaslInitialResponse withoutPayload(CString mechanismName) {
        return new SaslInitialResponse(mechanismName, false, ByteString.empty());
    }

    public static SaslInitialResponse of(CString mechanismName, ByteString payload) {
        return new SaslInitialResponse(mechanismName, true, payload);
    }

    public static SaslInitialResponse ofNullable(CString mechanismName, ByteString payload) {
        return payload == null ? withoutPayload(mechanismName) : of(mechanismName, payload);
    }

    /** Selected SASL authentication mechanism name. */
    public CString mechanismName() {
        return mechanismName;
    }

    /** Whether SASL payload data is present. */
    public boolean hasPayload() {
        return hasPayload;
    }

    /** SASL payload data. */
    public ByteString payload() {
        if (!hasPayload) {
            throw new IllegalStateException("No SASL payload is present");
        }
        return payload;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechanismName, Boolean.valueOf(hasPayload), payload);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof SaslInitialResponse)) {
            return false;
        }
        SaslInitialResponse otherSaslInitialResponse = (SaslInitialResponse) other;
        return
                mechanismName.equals(otherSaslInitialResponse.mechanismName) &&
                hasPayload == otherSaslInitialResponse.hasPayload &&
                payload.equals(otherSaslInitialResponse.payload);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mechanismName", mechanismName)
                .add("hasPayload", hasPayload)
                .add("payloadLength", hasPayload ? payload.length() : null)
                .build();
    }

}
