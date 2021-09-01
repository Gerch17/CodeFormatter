package format.formatter.commands;

import exceptions.WriterException;
import format.analysis.IToken;
import format.formatter.IContext;

public class CloseBracket implements ICommand {
    @Override
    public void execute(IToken token, IContext context) throws WriterException {
        context.decrementTab();
        context.writeNewLine();
        context.writeIndent();
        context.writeLexeme(token);
    }
}
