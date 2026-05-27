package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Describe command for a portal. */
public final class DescribePortalMessage implements TaggedMessage, FrontendMessage {

    private final CString portalName;

    public DescribePortalMessage(CString portalName) {
        this.portalName = Objects.requireNonNull(portalName, "portalName");
    }

    /** Name of the portal to describe. */
    public CString portalName() {
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
        } else if (!(other instanceof DescribePortalMessage)) {
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
