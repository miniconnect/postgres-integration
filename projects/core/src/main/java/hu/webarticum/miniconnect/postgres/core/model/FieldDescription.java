package hu.webarticum.miniconnect.postgres.core.model;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ToStringBuilder;

/** Metadata for one field in a RowDescriptionMessage message. */
public final class FieldDescription {

    private final String name;

    private final int tableObjectId;

    private final int columnAttributeNumber;

    private final int dataTypeObjectId;

    private final int dataTypeSize;

    private final int typeModifier;

    private final int formatCode;

    public FieldDescription(
            String name,
            int tableObjectId,
            int columnAttributeNumber,
            int dataTypeObjectId,
            int dataTypeSize,
            int typeModifier,
            int formatCode) {
        this.name = Objects.requireNonNull(name, "name");
        this.tableObjectId = tableObjectId;
        this.columnAttributeNumber = columnAttributeNumber;
        this.dataTypeObjectId = dataTypeObjectId;
        this.dataTypeSize = dataTypeSize;
        this.typeModifier = typeModifier;
        this.formatCode = formatCode;
    }

    /** Field name. */
    public String name() {
        return name;
    }

    /** Object ID of the source table, or zero when unavailable. */
    public int tableObjectId() {
        return tableObjectId;
    }

    /** Attribute number of the source table column, or zero when unavailable. */
    public int columnAttributeNumber() {
        return columnAttributeNumber;
    }

    /** Object ID of the field data type. */
    public int dataTypeObjectId() {
        return dataTypeObjectId;
    }

    /** Data type size, or negative for variable-width types. */
    public int dataTypeSize() {
        return dataTypeSize;
    }

    /** Type-specific modifier, or negative when unavailable. */
    public int typeModifier() {
        return typeModifier;
    }

    /** Format code used for the field. */
    public int formatCode() {
        return formatCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                tableObjectId,
                columnAttributeNumber,
                dataTypeObjectId,
                dataTypeSize,
                typeModifier,
                formatCode);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FieldDescription)) {
            return false;
        }
        FieldDescription otherFieldDescription = (FieldDescription) other;
        return tableObjectId == otherFieldDescription.tableObjectId
                && columnAttributeNumber == otherFieldDescription.columnAttributeNumber
                && dataTypeObjectId == otherFieldDescription.dataTypeObjectId
                && dataTypeSize == otherFieldDescription.dataTypeSize
                && typeModifier == otherFieldDescription.typeModifier
                && formatCode == otherFieldDescription.formatCode
                && name.equals(otherFieldDescription.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("tableObjectId", tableObjectId)
                .add("columnAttributeNumber", columnAttributeNumber)
                .add("dataTypeObjectId", dataTypeObjectId)
                .add("dataTypeSize", dataTypeSize)
                .add("typeModifier", typeModifier)
                .add("formatCode", formatCode)
                .build();
    }

}
