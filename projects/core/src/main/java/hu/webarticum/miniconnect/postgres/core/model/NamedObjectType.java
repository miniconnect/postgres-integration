package hu.webarticum.miniconnect.postgres.core.model;

/**
 * Prepared statement or portal target used by CloseMessage and DescribeMessage.
 */
public enum NamedObjectType {

    PREPARED_STATEMENT('S'),

    PORTAL('P');

    private final int code;

    private NamedObjectType(int code) {
        this.code = code;
    }

    /**
     * One-byte protocol code for this value.
     */
    public int getCode() {
        return code;
    }

}
