package io.reader.file;


import exceptions.CloseException;
import exceptions.ReaderException;
import io.reader.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader implements Reader {
    private final FileInputStream fin;

    public FileReader(File file) throws IOException {
        this.fin = new FileInputStream(file);
    }


    @Override
    public char readChar() throws ReaderException {
        try {
            return (char) fin.read();
        } catch (IOException e) {
            throw new ReaderException("Reader exception: file not found");
        }
    }

    @Override
    public boolean hasChar() throws ReaderException {
        try {
            return fin.available() > 0;
        } catch (IOException e) {
            throw new ReaderException("Reader exception: file not found");
        }
    }

    @Override
    public void close() throws CloseException {
        try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
