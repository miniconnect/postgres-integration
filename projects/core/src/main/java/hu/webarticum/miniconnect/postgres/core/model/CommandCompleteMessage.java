package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Command-completed response with a command tag. */
public final class CommandCompleteMessage implements TaggedMessage, BackendMessage {

    private final CString commandTag;

    public CommandCompleteMessage(CString commandTag) {
        this.commandTag = Objects.requireNonNull(commandTag, "commandTag");
    }

    /** Command tag identifying the completed SQL command. */
    public CString commandTag() {
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
        } else if (!(other instanceof CommandCompleteMessage)) {
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
