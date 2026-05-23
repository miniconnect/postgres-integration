package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Asynchronous notification delivered to a listening frontend. */
public final class NotificationResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'A';

    private final int processId;

    private final String channelName;

    private final String payload;

    public NotificationResponse(int processId, String channelName, String payload) {
        this.processId = processId;
        this.channelName = Objects.requireNonNull(channelName, "channelName");
        this.payload = Objects.requireNonNull(payload, "payload");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Process ID of the notifying backend. */
    public int processId() {
        return processId;
    }

    /** Name of the notification channel. */
    public String channelName() {
        return channelName;
    }

    /** Notification payload string. */
    public String payload() {
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
        }
        if (!(other instanceof NotificationResponse)) {
            return false;
        }
        NotificationResponse otherNotificationResponse = (NotificationResponse) other;
        return processId == otherNotificationResponse.processId
                && channelName.equals(otherNotificationResponse.channelName)
                && payload.equals(otherNotificationResponse.payload);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("processId", processId)
                .add("channelName", channelName)
                .add("payload", payload)
                .build();
    }

}
