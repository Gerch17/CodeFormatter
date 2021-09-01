package format.formatter.commands;

import exceptions.WriterException;
import format.analysis.IToken;
import format.formatter.IContext;

public class OpenBracket implements ICommand {
    @Override
    public void execute(IToken token, IContext context) throws WriterException {
        context.writeLexeme(token);
        context.writeNewLine();
        context.incrementTab();
    }
}
