package me.bredo.cmd.byteData.handlers;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TypeByteConverter {

    public static Integer arrayToInt(final byte[] array) {
        if (array.length != Integer.BYTES) return -1;
        return ByteBuffer.wrap(array).getInt();
    }

    public static byte[] intToArray(final Integer intValue) {
        return ByteBuffer.allocate(Integer.BYTES).putInt(intValue).array();
    }

    public static Float arrayToFloat(final byte[] array) {
        if (array.length != Float.BYTES) return -1f;
        return ByteBuffer.wrap(array).getFloat();
    }

    public static byte[] floatToArray(final Float floatValue) {
        return ByteBuffer.allocate(Float.BYTES).putFloat(floatValue).array();
    }

    public static Double arrayToDouble(final byte[] array) {
        if (array.length != Double.BYTES) return -1D;
        return ByteBuffer.wrap(array).getDouble();
    }

    public static byte[] doubleToArray(final Double doubleValue) {
        return ByteBuffer.allocate(Double.BYTES).putDouble(doubleValue).array();
    }

    public static Long arrayToLong(final byte[] array) {
        if (array.length != Long.BYTES) return -1L;
        return ByteBuffer.wrap(array).getLong();
    }

    public static byte[] longToArray(final Long longValue) {
        return ByteBuffer.allocate(Long.BYTES).putLong(longValue).array();
    }

    public static Short arrayToShort(final byte[] array) {
        if (array.length != Short.BYTES) return -1;
        return ByteBuffer.wrap(array).getShort();
    }

    public static byte[] shortToArray(final Short shortValue) {
        return ByteBuffer.allocate(Short.BYTES).putShort(shortValue).array();
    }

    public static Boolean byteToBoolean(final byte byteValue) {
        return byteValue == 1;
    }

    public static byte BooleanToByte(final Boolean booleanValue) {
        return (byte) (booleanValue ? 1 : 0);
    }

    public static String arrayToString(final byte[] array) {
        if (array.length < 4) return null;
        return new String(array, Integer.BYTES, arrayToInt(Arrays.copyOfRange(array, 0, Integer.BYTES)));
    }

    public static byte[] stringToArray(final String string) {
        final byte[] bytes = new byte[Integer.BYTES + string.length()];
        System.arraycopy(intToArray(string.length()), 0, bytes, 0, Integer.BYTES);
        System.arraycopy(string.getBytes(), 0, bytes, Integer.BYTES, string.length());
        return bytes;
    }

    public static String[] arrayToStringArray(final byte[] array) {
        if (array.length < 4) return null;
        final int length = arrayToInt(Arrays.copyOfRange(array, 0, Integer.BYTES));
        final String[] stringArray = new String[length];
        int currentLine = Integer.BYTES;
        for (int index = 0; index < length; index++) {
            final int stringLength = arrayToInt(Arrays.copyOfRange(array, currentLine, Integer.BYTES));
            currentLine += Integer.BYTES;
            stringArray[index] = arrayToString(Arrays.copyOfRange(array, currentLine, stringLength));
            currentLine += stringLength;
        }
        return stringArray;
    }

    public static byte[] stringArrayToArray(final String[] array) {
        final byte[] arrayLengthBytes = intToArray(array.length);
        int arrayLength = arrayLengthBytes.length;
        for (final String string : array) arrayLength += Integer.BYTES + string.length();

        final byte[] bytes = new byte[arrayLength];
        System.arraycopy(arrayLengthBytes, 0, bytes, 0, arrayLengthBytes.length);
        int currentLine = arrayLengthBytes.length;

        for (final String string : array) {
            System.arraycopy(intToArray(string.length()), 0, bytes, currentLine, Integer.BYTES);
            System.arraycopy(string.getBytes(), 0, bytes, currentLine + Integer.BYTES, string.length());
            currentLine += Integer.BYTES + string.length();
        }

        return bytes;
    }
}