package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/**
 * Ready-for-query message with transaction status.
 */
public final class ReadyForQueryMessage implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'Z';

    private final TransactionStatus transactionStatus;

    public ReadyForQueryMessage(TransactionStatus transactionStatus) {
        this.transactionStatus = Objects.requireNonNull(transactionStatus, "transactionStatus");
    }

    /**
     * One-byte message type code used on the wire.
     */
    @Override
    public int getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Current backend transaction status.
     */
    public TransactionStatus getTransactionStatus() {
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
        }
        if (!(other instanceof ReadyForQueryMessage)) {
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
