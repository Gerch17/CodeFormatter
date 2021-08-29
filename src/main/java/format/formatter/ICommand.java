package format.formatter;

import exceptions.WriterException;
import io.writer.Writer;

public interface ICommand {
    void runCommand(Writer writer, int tab, String append) throws WriterException;
}
