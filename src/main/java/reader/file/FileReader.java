package reader.file;

import reader.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader implements Reader {
    private final FileInputStream fin;

    public FileReader(File file) throws IOException {
        this.fin = new FileInputStream(file);
    }


    @Override
    public char readChar() throws IOException {
        return (char) fin.read();
    }

    @Override
    public boolean hasChar() throws IOException {
        return fin.available() > 0;
    }

    @Override
    public void close() throws Exception {
        fin.close();
    }
}
