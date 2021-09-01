package format.formatter.commands;

import exceptions.WriterException;
import format.analysis.IToken;
import format.formatter.IContext;
import io.writer.Writer;

public interface ICommand {
    void execute(IToken token, IContext context) throws WriterException;
}
