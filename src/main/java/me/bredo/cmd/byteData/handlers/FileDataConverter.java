package me.bredo.cmd.byteData.handlers;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class FileDataConverter {

    private byte[] dataBuffer;
    private int currentIndex;

    public FileDataConverter(final byte[] dataBuffer) {
        this.dataBuffer = dataBuffer;
        currentIndex = 0;
    }

    public byte[] getRestDataBuffer() {
        return Arrays.copyOfRange(dataBuffer, currentIndex, dataBuffer.length);
    }

    public Object convert(final Class<?> classType) {
        Object object = null;

        if (classType.equals(String.class)) {
            final int length = ByteBuffer.wrap(dataBuffer, currentIndex, Integer.BYTES).getInt();
            currentIndex += Integer.BYTES;
            object = new String(dataBuffer, currentIndex, length);
            currentIndex += length;
        } else if (classType.equals(Integer.class)) {
            object = ByteBuffer.wrap(dataBuffer, currentIndex, Integer.BYTES).getInt();
            currentIndex += Integer.BYTES;
        } else if (classType.equals(Boolean.class)) {
            object = dataBuffer[currentIndex] == (byte) 1;
            currentIndex++;
        } else if (classType.equals(Float.class)) {
            object = ByteBuffer.wrap(dataBuffer, currentIndex, Float.BYTES).getFloat();
            currentIndex += Float.BYTES;
        } else if (classType.equals(Double.class)) {
            object = ByteBuffer.wrap(dataBuffer, currentIndex, Double.BYTES).getDouble();
            currentIndex += Double.BYTES;
        } else if (classType.equals(Short.class)) {
            object = ByteBuffer.wrap(dataBuffer, currentIndex, Short.BYTES).getShort();
            currentIndex += Short.BYTES;
        } else if (classType.equals(Long.class)) {
            object = ByteBuffer.wrap(dataBuffer, currentIndex, Long.BYTES).getLong();
            currentIndex += Long.BYTES;
        } else if (classType.equals(String[].class)) {
            final int stringArrayLength = ByteBuffer.wrap(dataBuffer, currentIndex, Integer.BYTES).getInt();
            currentIndex += Integer.BYTES;
            final String[] stringArray = new String[stringArrayLength];

            for (int index = 0; index < stringArrayLength; index++) {
                final int arrayLength = ByteBuffer.wrap(dataBuffer, currentIndex, Integer.BYTES).getInt();
                currentIndex += Integer.BYTES;
                stringArray[index] = new String(dataBuffer, currentIndex, arrayLength);
                currentIndex += arrayLength;
            }
            object = stringArray;
        } else if (classType.isEnum()) {
            System.out.println("enum!");
            final int length = ByteBuffer.wrap(dataBuffer, currentIndex, Integer.BYTES).getInt();
            currentIndex += Integer.BYTES;
            object = new String(dataBuffer, currentIndex, length);
            currentIndex += length;
        }
        return object;
    }
}
