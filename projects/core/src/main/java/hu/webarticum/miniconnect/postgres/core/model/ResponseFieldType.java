package hu.webarticum.miniconnect.postgres.core.model;

/** Known error and notice response field type. */
public enum ResponseFieldType {

    SEVERITY('S'),
    SEVERITY_NON_LOCALIZED('V'),
    SQLSTATE('C'),
    MESSAGE('M'),
    DETAIL('D'),
    HINT('H'),
    POSITION('P'),
    INTERNAL_POSITION('p'),
    INTERNAL_QUERY('q'),
    WHERE('W'),
    SCHEMA_NAME('s'),
    TABLE_NAME('t'),
    COLUMN_NAME('c'),
    DATA_TYPE_NAME('d'),
    CONSTRAINT_NAME('n'),
    FILE('F'),
    LINE('L'),
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
