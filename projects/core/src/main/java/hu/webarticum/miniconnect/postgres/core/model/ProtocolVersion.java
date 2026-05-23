package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** PostgreSQL protocol version encoded as major and minor 16-bit parts. */
public final class ProtocolVersion {

    public static final ProtocolVersion V3_0 = new ProtocolVersion(3, 0);

    public static final ProtocolVersion V3_1 = new ProtocolVersion(3, 1);

    public static final ProtocolVersion V3_2 = new ProtocolVersion(3, 2);

    private final int major;

    private final int minor;

    private ProtocolVersion(int major, int minor) {
        checkVersionPart(major, "major");
        checkVersionPart(minor, "minor");
        this.major = major;
        this.minor = minor;
    }

    public static ProtocolVersion of(int major, int minor) {
        if (major == V3_0.major && minor == V3_0.minor) {
            return V3_0;
        } else if (major == V3_1.major && minor == V3_1.minor) {
            return V3_1;
        } else if (major == V3_2.major && minor == V3_2.minor) {
            return V3_2;
        }
        return new ProtocolVersion(major, minor);
    }

    public static ProtocolVersion ofInt(int value) {
        return of((value >>> 16) & 0xFFFF, value & 0xFFFF);
    }

    /** Major protocol version part. */
    public int major() {
        return major;
    }

    /** Minor protocol version part. */
    public int minor() {
        return minor;
    }

    /** Protocol version encoded as a single 32-bit integer. */
    public int asInt() {
        return (major << 16) | minor;
    }

    private static void checkVersionPart(int value, String name) {
        if (value < 0 || value > 0xFFFF) {
            throw new IllegalArgumentException(name + " must be in range 0..65535");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(major), Integer.valueOf(minor));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProtocolVersion)) {
            return false;
        }
        ProtocolVersion otherProtocolVersion = (ProtocolVersion) other;
        return major == otherProtocolVersion.major
                && minor == otherProtocolVersion.minor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("major", major)
                .add("minor", minor)
                .build();
    }

}
