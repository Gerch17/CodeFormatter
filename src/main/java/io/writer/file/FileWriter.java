package io.writer.file;

import exceptions.CloseException;
import exceptions.WriterException;
import io.writer.Writer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter implements Writer {
    private final FileOutputStream fout;

    public FileWriter(File file) throws FileNotFoundException {
        this.fout = new FileOutputStream(file);
    }

    @Override
    public void writeChar(char ch) throws WriterException {
        try {
            fout.write(ch);
        } catch (IOException e) {
            throw new WriterException("Writer exception: file not found");
        }
    }

    @Override
    public void close() throws CloseException {
        try {
            fout.close();
        } catch (IOException e) {
            throw new CloseException("Writer exception: file not found");
        }
    }
}
