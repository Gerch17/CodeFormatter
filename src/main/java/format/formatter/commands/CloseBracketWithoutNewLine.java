package format.formatter.commands;

import exceptions.WriterException;
import format.analysis.IToken;
import format.formatter.IContext;

public class CloseBracketWithoutNewLine implements ICommand {
    @Override
    public void execute(IToken token, IContext context) throws WriterException {
        context.decrementTab();
        context.writeIndent();
        context.writeLexeme(token);
        context.writeNewLine();
    }
}
