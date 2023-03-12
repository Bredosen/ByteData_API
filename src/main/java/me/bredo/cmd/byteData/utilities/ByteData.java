package me.bredo.cmd.byteData.utilities;

import me.bredo.cmd.byteData.handlers.TypeByteConverter;

public final class ByteData<T> {

    private T type;

    public ByteData(final T type) {
        set(type);
    }

    public ByteData() {
    }

    public T get() {
        return type;
    }

    public void set(final T type) {
        this.type = type;
    }

    public void convertFrom(final byte[] bytes) {
        if (type instanceof String string) {
            type = (T) TypeByteConverter.arrayToString(bytes);
        } else if (type instanceof Integer integer) {
            type = (T) TypeByteConverter.arrayToInt(bytes);
        } else if (type instanceof Float floatValue) {
            type = (T) TypeByteConverter.arrayToFloat(bytes);
        } else if (type instanceof Double doubleValue) {
            type = (T) TypeByteConverter.arrayToDouble(bytes);
        } else if (type instanceof Short shortValue) {
            type = (T) TypeByteConverter.arrayToShort(bytes);
        } else if (type instanceof Long longValue) {
            type = (T) TypeByteConverter.arrayToLong(bytes);
        } else if (type instanceof Boolean booleanValue) {
            type = (T) TypeByteConverter.byteToBoolean(bytes[0]);
        } else if (type instanceof String[] stringArray) {
            type = (T) TypeByteConverter.arrayToStringArray(bytes);
        } else {
            System.err.println("Type '" + type.getClass().getSimpleName() + "'could not be converted");
        }
    }

    public byte[] convert() {
        byte[] bytes = null;
        if (type instanceof String string) {
            bytes = TypeByteConverter.stringToArray(string);
        } else if (type instanceof Integer integer) {
            bytes = TypeByteConverter.intToArray(integer);
        } else if (type instanceof Float floatValue) {
            bytes = TypeByteConverter.floatToArray(floatValue);
        } else if (type instanceof Double doubleValue) {
            bytes = TypeByteConverter.doubleToArray(doubleValue);
        } else if (type instanceof Short shortValue) {
            bytes = TypeByteConverter.shortToArray(shortValue);
        } else if (type instanceof Long longValue) {
            bytes = TypeByteConverter.longToArray(longValue);
        } else if (type instanceof Boolean booleanValue) {
            bytes = new byte[]{TypeByteConverter.BooleanToByte(booleanValue)};
        } else if (type instanceof String[] stringArray) {
            bytes = TypeByteConverter.stringArrayToArray(stringArray);
        } else if (type.getClass().isEnum()) {
            bytes = TypeByteConverter.stringToArray(type.toString());
        } else {
            System.err.println("Type '" + type.getClass().getSimpleName() + "'could not be converted");
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "ByteData{type=" + type.getClass().getSimpleName() + ", value=" + type + '}';
    }
}
