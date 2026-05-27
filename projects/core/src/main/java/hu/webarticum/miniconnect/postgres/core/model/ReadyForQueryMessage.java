package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Ready-for-query message with transaction status. */
public final class ReadyForQueryMessage implements TaggedMessage, BackendMessage {

    private final TransactionStatus transactionStatus;

    public ReadyForQueryMessage(TransactionStatus transactionStatus) {
        this.transactionStatus = Objects.requireNonNull(transactionStatus, "transactionStatus");
    }

    /** Current backend transaction status. */
    public TransactionStatus transactionStatus() {
        return transactionStatus;
    }

    @Override
    public int hashCode() {
        return transactionStatus.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ReadyForQueryMessage)) {
            return false;
        }
        ReadyForQueryMessage otherReadyForQuery = (ReadyForQueryMessage) other;
        return transactionStatus == otherReadyForQuery.transactionStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactionStatus", transactionStatus)
                .build();
    }

}
