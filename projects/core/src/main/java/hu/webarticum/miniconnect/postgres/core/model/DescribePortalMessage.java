package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Describe command for a portal. */
public final class DescribePortalMessage implements TaggedMessage, FrontendMessage {

    public static final int MESSAGE_TYPE = 'D';

    private final String portalName;

    public DescribePortalMessage(String portalName) {
        this.portalName = Objects.requireNonNull(portalName, "portalName");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Name of the portal to describe. */
    public String portalName() {
        return portalName;
    }

    @Override
    public int hashCode() {
        return portalName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DescribePortalMessage)) {
            return false;
        }
        DescribePortalMessage otherDescribePortal = (DescribePortalMessage) other;
        return portalName.equals(otherDescribePortal.portalName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("portalName", portalName)
                .build();
    }

}
