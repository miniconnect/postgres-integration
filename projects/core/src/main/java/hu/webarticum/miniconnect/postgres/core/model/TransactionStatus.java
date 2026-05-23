package hu.webarticum.miniconnect.postgres.core.model;

/** ReadyForQueryMessage transaction status code. */
public enum TransactionStatus {

    IDLE('I'),
    IN_TRANSACTION('T'),
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
