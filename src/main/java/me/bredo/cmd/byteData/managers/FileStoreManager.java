package me.bredo.cmd.byteData.managers;


import me.bredo.cmd.byteData.utilities.FileStore;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiConsumer;

public final class FileStoreManager {

    private final static FileStoreManager SINGLETON;

    static {
        SINGLETON = new FileStoreManager();
    }

    private final HashMap<String, FileStore> fileStoreMatrix;

    public FileStoreManager() {
        fileStoreMatrix = new HashMap<>();
    }

    public static FileStoreManager instance() {
        return SINGLETON;
    }

    public FileStore add(final String callName, final FileStore fileStore) {
        getMatrix().put(callName, fileStore);
        return fileStore;
    }

    public void remove(final String callName) {
        getMatrix().remove(callName);
    }

    public void removeAll() {
        getMatrix().clear();
    }

    public boolean contain(final String callName) {
        return getMatrix().containsKey(callName);
    }

    public FileStore get(final String callName) {
        return getMatrix().get(callName);
    }

    public int size() {
        return getMatrix().size();
    }

    public void forEach(final BiConsumer<? super String, ? super FileStore> action) {
        getMatrix().forEach(action);
    }

    public HashMap<String, FileStore> getMatrix() {
        return fileStoreMatrix;
    }

    public String getJarPath() {
        try {
            return new File(".").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
