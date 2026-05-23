package hu.webarticum.miniconnect.postgres.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Notice message made of identified string fields. */
public final class NoticeResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'N';

    public static final char FIELD_SEVERITY = 'S';

    public static final char FIELD_SEVERITY_NON_LOCALIZED = 'V';

    public static final char FIELD_SQLSTATE = 'C';

    public static final char FIELD_MESSAGE = 'M';

    public static final char FIELD_DETAIL = 'D';

    public static final char FIELD_HINT = 'H';

    public static final char FIELD_POSITION = 'P';

    public static final char FIELD_INTERNAL_POSITION = 'p';

    public static final char FIELD_INTERNAL_QUERY = 'q';

    public static final char FIELD_WHERE = 'W';

    public static final char FIELD_SCHEMA_NAME = 's';

    public static final char FIELD_TABLE_NAME = 't';

    public static final char FIELD_COLUMN_NAME = 'c';

    public static final char FIELD_DATA_TYPE_NAME = 'd';

    public static final char FIELD_CONSTRAINT_NAME = 'n';

    public static final char FIELD_FILE = 'F';

    public static final char FIELD_LINE = 'L';

    public static final char FIELD_ROUTINE = 'R';

    private final ImmutableList<ResponseField> fields;

    public NoticeResponse(ImmutableList<ResponseField> fields) {
        this.fields = Objects.requireNonNull(fields, "fields");
        for (ResponseField field : fields) {
            Objects.requireNonNull(field, "field");
        }
    }

    public NoticeResponse(Map<Character, String> fields) {
        this(toFields(fields));
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Identified notice fields in protocol form. */
    public ImmutableList<ResponseField> fields() {
        return fields;
    }

    /** Value of a single identified notice field. */
    public String field(char fieldType) {
        for (int i = fields.size() - 1; i >= 0; i--) {
            ResponseField field = fields.get(i);
            if (field.type() == fieldType) {
                return field.value();
            }
        }
        return null;
    }

    private static ImmutableList<ResponseField> toFields(Map<Character, String> fields) {
        Objects.requireNonNull(fields, "fields");
        List<ResponseField> resultBuilder = new ArrayList<ResponseField>(fields.size());
        for (Map.Entry<Character, String> entry : fields.entrySet()) {
            Character type = Objects.requireNonNull(entry.getKey(), "field type");
            String value = Objects.requireNonNull(entry.getValue(), "field value");
            resultBuilder.add(new ResponseField(type.charValue(), value));
        }
        return ImmutableList.fromCollection(resultBuilder);
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NoticeResponse)) {
            return false;
        }
        NoticeResponse otherNoticeResponse = (NoticeResponse) other;
        return fields.equals(otherNoticeResponse.fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fields", fields)
                .build();
    }

}
