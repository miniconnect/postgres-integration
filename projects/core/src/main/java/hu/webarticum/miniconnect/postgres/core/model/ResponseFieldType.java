package hu.webarticum.miniconnect.postgres.core.model;

/** Known error and notice response field type. */
public enum ResponseFieldType {

    /** Localized severity. */
    SEVERITY('S'),

    /** Non-localized severity. */
    SEVERITY_NON_LOCALIZED('V'),

    /** SQLSTATE error code. */
    SQLSTATE('C'),

    /** Primary human-readable message. */
    MESSAGE('M'),

    /** Optional detail message. */
    DETAIL('D'),

    /** Optional suggestion about what to do next. */
    HINT('H'),

    /** Cursor position in the original query text. */
    POSITION('P'),

    /** Cursor position in an internally generated query. */
    INTERNAL_POSITION('p'),

    /** Internally generated query text. */
    INTERNAL_QUERY('q'),

    /** Context describing where the error occurred. */
    WHERE('W'),

    /** Related schema name. */
    SCHEMA_NAME('s'),

    /** Related table name. */
    TABLE_NAME('t'),

    /** Related column name. */
    COLUMN_NAME('c'),

    /** Related data type name. */
    DATA_TYPE_NAME('d'),

    /** Related constraint name. */
    CONSTRAINT_NAME('n'),

    /** Source-code file name. */
    FILE('F'),

    /** Source-code line number. */
    LINE('L'),

    /** Source-code routine name. */
    ROUTINE('R'),

    ;

    private final char code;

    private ResponseFieldType(char code) {
        this.code = code;
    }

    /** One-byte field type code used on the wire. */
    public char code() {
        return code;
    }

    /** Response field type matching the given type code. */
    public static ResponseFieldType ofCode(char code) {
        for (ResponseFieldType fieldType : values()) {
            if (fieldType.code == code) {
                return fieldType;
            }
        }
        throw new IllegalArgumentException("Unknown response field type code: " + code);
    }

}
