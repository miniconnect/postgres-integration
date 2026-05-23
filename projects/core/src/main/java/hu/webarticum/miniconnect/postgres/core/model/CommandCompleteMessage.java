package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Command-completed response with a command tag. */
public final class CommandCompleteMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'C';

    private final String commandTag;

    public CommandCompleteMessage(String commandTag) {
        this.commandTag = Objects.requireNonNull(commandTag, "commandTag");
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Command tag identifying the completed SQL command. */
    public String commandTag() {
        return commandTag;
    }

    @Override
    public int hashCode() {
        return commandTag.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommandCompleteMessage)) {
            return false;
        }
        CommandCompleteMessage otherCommandComplete = (CommandCompleteMessage) other;
        return commandTag.equals(otherCommandComplete.commandTag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("commandTag", commandTag)
                .build();
    }

}
