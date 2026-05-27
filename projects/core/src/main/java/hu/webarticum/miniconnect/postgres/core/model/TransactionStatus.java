package hu.webarticum.miniconnect.postgres.core.model;

/** ReadyForQueryMessage transaction status code. */
public enum TransactionStatus {

    /** Not in a transaction block. */
    IDLE('I'),

    /** In a transaction block. */
    IN_TRANSACTION('T'),

    /** In a failed transaction block. */
    FAILED_TRANSACTION('E'),

    ;

    private final int code;

    private TransactionStatus(int code) {
        this.code = code;
    }

    /** One-byte protocol code for this value. */
    public int code() {
        return code;
    }

}
