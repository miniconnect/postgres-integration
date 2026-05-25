package hu.webarticum.miniconnect.postgres.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Error response made of protocol fields. */
public final class ErrorResponse implements TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'E';

    private final ImmutableList<ResponseField> fields;

    public ErrorResponse(ImmutableList<ResponseField> fields) {
        this.fields = Objects.requireNonNull(fields, "fields");
        for (ResponseField field : fields) {
            Objects.requireNonNull(field, "field");
        }
    }

    public ErrorResponse(Map<Character, String> fields) {
        this(toFields(fields));
    }

    /** One-byte message type code used on the wire. */
    @Override
    public int messageType() {
        return MESSAGE_TYPE;
    }

    /** Error fields in protocol order. */
    public ImmutableList<ResponseField> fields() {
        return fields;
    }

    /** Value of the last error field with the given type. */
    public String field(ResponseFieldType fieldType) {
        return field(Objects.requireNonNull(fieldType, "fieldType").code());
    }

    /** Value of the last error field with the given type code. */
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
        if (!(other instanceof ErrorResponse)) {
            return false;
        }
        ErrorResponse otherErrorResponse = (ErrorResponse) other;
        return fields.equals(otherErrorResponse.fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fields", fields)
                .build();
    }

}
