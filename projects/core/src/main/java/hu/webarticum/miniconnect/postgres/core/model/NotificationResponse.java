package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Asynchronous notification delivered to a listening frontend. */
public final class NotificationResponse implements TaggedMessage, BackendMessage {

    private final int processId;

    private final CString channelName;

    private final CString payload;

    public NotificationResponse(int processId, CString channelName, CString payload) {
        this.processId = processId;
        this.channelName = Objects.requireNonNull(channelName, "channelName");
        this.payload = Objects.requireNonNull(payload, "payload");
    }

    /** Process ID of the notifying backend. */
    public int processId() {
        return processId;
    }

    /** Name of the notification channel. */
    public CString channelName() {
        return channelName;
    }

    /** Notification payload string. */
    public CString payload() {
        return payload;
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, channelName, payload);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof NotificationResponse)) {
            return false;
        }
        NotificationResponse otherNotificationResponse = (NotificationResponse) other;
        return
                processId == otherNotificationResponse.processId &&
                channelName.equals(otherNotificationResponse.channelName) &&
                payload.equals(otherNotificationResponse.payload);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("processId", processId)
                .add("channelName", channelName)
                .add("payloadByteLength", payload.bytes().length())
                .build();
    }

}
