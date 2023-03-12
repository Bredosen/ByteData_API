package me.bredo.cmd.byteData.utilities;

import me.bredo.cmd.byteData.handlers.FileDataConverter;

import java.util.function.Consumer;

public final class ByteDataStore {

    private byte[] dataBuffer;

    public ByteDataStore() {
        this(0);
    }

    public ByteDataStore(final int bufferLength) {
        this(new byte[bufferLength]);
    }

    public ByteDataStore(final byte[] dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    public void allocateBuffer(final int bufferLength) {
        final byte[] tempDataBuffer = dataBuffer.clone();
        dataBuffer = new byte[bufferLength];
        System.arraycopy(tempDataBuffer, 0, dataBuffer, 0, Math.min(tempDataBuffer.length, bufferLength));
    }


    public void append(final ByteData byteData) {
        append(byteData.convert());
    }

    public void append(final ByteDataStore byteDataStore) {
        append(byteDataStore.getDataBuffer());
    }

    public void append(final byte[] bytes) {
        final int currentSize = size();
        allocateBuffer(currentSize + bytes.length);
        System.arraycopy(bytes, 0, getDataBuffer(), currentSize, bytes.length);
    }

    public void set(final int index, final byte byt) {
        if (index < 0 || index > size()) return;
        dataBuffer[index] = byt;
    }

    public byte get(final int index) {
        if (index < 0 || index > size()) return -1;
        return dataBuffer[index];
    }

    public int size() {
        return dataBuffer.length;
    }

    public boolean isEmpty() {
        return getDataBuffer() == null | size() == 0;
    }

    public byte[] getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(final byte[] dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    public void convertData(final Consumer<? super FileDataConverter> action) {
        action.accept(new FileDataConverter(getDataBuffer()));
    }
}
