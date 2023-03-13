package me.bredo.cmd.byteData.utilities;

import me.bredo.cmd.byteData.handlers.TypeByteConverter;

/**
 * ByteData class represents data in byte format, and provides methods to convert it to and from Java data types.
 * It also stores the data type.
 */
public final class ByteData {
    private final ByteDataType byteDataType;
    private Object value;

    /**
     * Creates a new ByteData object with the given ByteDataType and value.
     *
     * @param byteDataType The ByteDataType of the value.
     * @param value        The value to store.
     */
    public ByteData(final ByteDataType byteDataType, final Object value) {
        this.byteDataType = byteDataType;
        set(value);
    }

    /**
     * Creates a new ByteData object with a dynamically detected ByteDataType and value.
     *
     * @param value The value to store.
     */
    public ByteData(final Object value) {
        if (value instanceof String) byteDataType = ByteDataType.String;
        else if (value instanceof Integer) byteDataType = ByteDataType.Int;
        else if (value instanceof Float) byteDataType = ByteDataType.Float;
        else if (value instanceof Double) byteDataType = ByteDataType.Double;
        else if (value instanceof Short) byteDataType = ByteDataType.Short;
        else if (value instanceof Long) byteDataType = ByteDataType.Long;
        else if (value instanceof Boolean) byteDataType = ByteDataType.Boolean;
        else if (value instanceof String[]) byteDataType = ByteDataType.String_Array;
        else {
            byteDataType = null;
            return;
        }
        set(value);
    }

    /**
     * Gets the stored value.
     *
     * @return The stored value.
     */
    public Object get() {
        return value;
    }

    /**
     * Sets the stored value.
     *
     * @param value The value to store.
     */
    public void set(final Object value) {
        this.value = value;
    }

    /**
     * Gets the ByteDataType of the stored value.
     *
     * @return The ByteDataType of the stored value.
     */
    public ByteDataType getByteDataType() {
        return byteDataType;
    }

    /**
     * Converts the stored value from a byte array.
     *
     * @param bytes The byte array to convert from.
     */
    public void convertFrom(final byte[] bytes) {
        if (bytes == null) return;
        if (byteDataType.equals(ByteDataType.String)) set(TypeByteConverter.arrayToString(bytes));
        else if (byteDataType.equals(ByteDataType.Int)) set(TypeByteConverter.arrayToInt(bytes));
        else if (byteDataType.equals(ByteDataType.Float)) set(TypeByteConverter.arrayToFloat(bytes));
        else if (byteDataType.equals(ByteDataType.Double)) set(TypeByteConverter.arrayToDouble(bytes));
        else if (byteDataType.equals(ByteDataType.Short)) set(TypeByteConverter.arrayToShort(bytes));
        else if (byteDataType.equals(ByteDataType.Long)) set(TypeByteConverter.arrayToLong(bytes));
        else if (byteDataType.equals(ByteDataType.Boolean)) set(TypeByteConverter.byteToBoolean(bytes[0]));
        else if (byteDataType.equals(ByteDataType.String_Array)) set(TypeByteConverter.arrayToStringArray(bytes));
        else System.err.println("Type '" + value.getClass().getSimpleName() + "'could not be converted");
    }

    /**
     * Converts the stored value to a byte array based on the ByteDataType.
     *
     * @return a byte array representation of the stored value
     */
    public byte[] convert() {
        if (get() == null) return new byte[0];
        byte[] bytes = null;
        if (byteDataType.equals(ByteDataType.String)) bytes = TypeByteConverter.stringToArray((String) value);
        else if (byteDataType.equals(ByteDataType.Int)) bytes = TypeByteConverter.intToArray((Integer) value);
        else if (byteDataType.equals(ByteDataType.Float)) bytes = TypeByteConverter.floatToArray((Float) value);
        else if (byteDataType.equals(ByteDataType.Double)) bytes = TypeByteConverter.doubleToArray((Double) value);
        else if (byteDataType.equals(ByteDataType.Short)) bytes = TypeByteConverter.shortToArray((Short) value);
        else if (byteDataType.equals(ByteDataType.Long)) bytes = TypeByteConverter.longToArray((Long) value);
        else if (byteDataType.equals(ByteDataType.Boolean)) bytes = new byte[]{TypeByteConverter.BooleanToByte((Boolean) value)};
        else if (byteDataType.equals(ByteDataType.String_Array)) bytes = TypeByteConverter.stringArrayToArray((String[]) value);
        else System.err.println("Type '" + value.getClass().getSimpleName() + "' could not be converted");

        return bytes;
    }

    /**
     * Returns a String representation of the ByteData object.
     *
     * @return a String representation of the ByteData object
     */
    @Override
    public String toString() {
        return "ByteData{ByteDataType=" + byteDataType + ", value=" + value + '}';
    }
}