package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Cancellation secret key used by BackendKeyData and CancelRequest messages. */
public final class CancellationKey {

    /** Minimum cancellation secret key length in bytes. */
    public static final int MIN_LENGTH = 4;

    /** Maximum cancellation secret key length in bytes. */
    public static final int MAX_LENGTH = 256;

    private final ByteString bytes;

    /**
     * Creates a cancellation secret key.
     *
     * <p>
     * Starting with protocol version 3.2, it is dynamically sized
     * and must contain {@value #MIN_LENGTH}..{@value #MAX_LENGTH} bytes.
     * The cancellation secret key was fixed at 4 bytes before protocol version 3.2.
     */
    public CancellationKey(ByteString bytes) {
        this.bytes = Objects.requireNonNull(bytes, "bytes");
        int length = bytes.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "bytes length must be in range " + MIN_LENGTH + ".." + MAX_LENGTH + " bytes, but was " + length);
        }
    }

    /** Secret key bytes. */
    public ByteString bytes() {
        return bytes;
    }

    @Override
    public int hashCode() {
        return bytes.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof CancellationKey)) {
            return false;
        }
        CancellationKey otherCancellationKey = (CancellationKey) other;
        return bytes.equals(otherCancellationKey.bytes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("length", bytes.length())
                .build();
    }

}
