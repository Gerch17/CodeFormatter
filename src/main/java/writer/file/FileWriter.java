package writer.file;

import writer.Writer;

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
    public void writeChar(char ch) throws IOException {
        fout.write(ch);
    }

    @Override
    public void close() throws Exception {
        fout.close();
    }
}
