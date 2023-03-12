package me.bredo.cmd.byteData.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public final class FileStore {

    private final String path;
    private final String name;
    private final String extension;

    private File directory;
    private File file;

    public FileStore(final String path, final String name, final String extension) {
        this.path = path;
        this.name = name;
        this.extension = extension;

        // Set directory
        final File directory = new File(path);
        this.directory = directory;

        // Set file
        final File file = new File(path + "/" + name + "." + extension);
        this.file = file;
    }

    public boolean create() {
        return createDirectory() && createFile();
    }

    public boolean createDirectory() {
        if (directoryExists()) return true;
        return directory.mkdirs();
    }

    public File getDirectory() {
        return directory;
    }

    public boolean directoryExists() {
        return getDirectory().exists() && getDirectory().isDirectory();
    }

    public boolean createFile() {
        if (fileExists()) return true;
        try {
            file.createNewFile();
            return true;
        } catch (final IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public File getFile() {
        return file;
    }

    public boolean fileExists() {
        return getFile().exists() && getFile().isFile();
    }

    public ByteDataStore retrieveData() {
        if (!fileExists()) return null;
        try {
            return new ByteDataStore(Files.readAllBytes(getFile().toPath()));
        } catch (final IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean saveData(final ByteDataStore byteDataStore) throws IOException {
        if (!fileExists()) return false;
        if (byteDataStore == null) return false;
        try (FileOutputStream fos = new FileOutputStream(getFile())) {
            fos.write(byteDataStore.getDataBuffer());
            return true;
        }
    }
}
