package hu.webarticum.miniconnect.postgres.core.model;

/** PostgreSQL data format code. */
public enum FormatCode {

    /** Text representation. */
    TEXT(0),

    /** Binary representation. */
    BINARY(1),

    ;

    private final int code;

    private FormatCode(int code) {
        this.code = code;
    }

    /** Numeric format code used on the wire. */
    public int code() {
        return code;
    }

    /** Format code matching the given numeric code. */
    public static FormatCode ofCode(int code) {
        for (FormatCode formatCode : values()) {
            if (formatCode.code == code) {
                return formatCode;
            }
        }
        throw new IllegalArgumentException("Unknown format code: " + code);
    }

}
