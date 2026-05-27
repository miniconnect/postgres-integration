package hu.webarticum.miniconnect.postgres.core.model;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import hu.webarticum.miniconnect.lang.ByteString;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Null-terminated protocol string value without the terminating zero byte. */
public final class CString {

    private final ByteString bytes;

    /** Creates a CString from protocol bytes without the terminating NUL byte. */
    private CString(ByteString bytes) {
        this.bytes = Objects.requireNonNull(bytes, "bytes");
        if (bytes.indexOf((byte) 0) >= 0) {
            throw new IllegalArgumentException("CString bytes must not contain NUL bytes");
        }
    }

    /** Creates a CString from a Java string encoded as UTF-8. */
    public static CString ofString(String value) {
        return ofString(value, StandardCharsets.UTF_8);
    }

    /** Creates a CString from a Java string encoded with the given charset. */
    public static CString ofString(String value, Charset charset) {
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(charset, "charset");
        return ofBytes(ByteString.of(value, charset));
    }

    /** Creates a CString from protocol bytes without the terminating NUL byte. */
    public static CString ofBytes(ByteString bytes) {
        return new CString(bytes);
    }

    /** Protocol bytes without the terminating NUL byte. */
    public ByteString bytes() {
        return bytes;
    }

    /** Protocol bytes including the terminating NUL byte. */
    public ByteString bytesWithNull() {
        return ByteString.builder()
                .append(bytes)
                .append((byte) 0)
                .build();
    }

    /** Decoded string value using the given charset. */
    public String stringValue(Charset charset) {
        return bytes.toString(charset);
    }

    /** Decoded string value using UTF-8. */
    public String utf8Value() {
        return stringValue(StandardCharsets.UTF_8);
    }

    @Override
    public int hashCode() {
        return bytes.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof CString)) {
            return false;
        }
        CString otherCString = (CString) other;
        return bytes.equals(otherCString.bytes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("byteLength", bytes.length())
                .build();
    }

}
