package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Close command for a portal. */
public final class ClosePortalMessage implements TaggedMessage, FrontendMessage {

    private final CString portalName;

    public ClosePortalMessage(CString portalName) {
        this.portalName = Objects.requireNonNull(portalName, "portalName");
    }

    /** Name of the portal to close. */
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
        } else if (!(other instanceof ClosePortalMessage)) {
            return false;
        }
        ClosePortalMessage otherClosePortal = (ClosePortalMessage) other;
        return portalName.equals(otherClosePortal.portalName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("portalName", portalName)
                .build();
    }

}
